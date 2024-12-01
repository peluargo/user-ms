package app.peluargo.user.api;

import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Given a valid body, when calling the \"create\" endpoint, should return valid user")
    void create201() throws Exception {
        UserCreationDTO userCreationDTO = new UserCreationDTO(
                "John",
                "Doe",
                LocalDate.parse("2000-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "john.doe@example.com"
        );

        // this is done so we can stringify LocalDate type fields
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String requestBody = gson.toJson(userCreationDTO);

        UserDTO expedtedUser = new UserDTO(
                UUID.randomUUID(),
                userCreationDTO.firstName(),
                userCreationDTO.lastName(),
                userCreationDTO.birthdate(),
                userCreationDTO.email()
        );

        Mockito
                .when(this.userService.create(Mockito.any(UserCreationDTO.class)))
                .thenReturn(expedtedUser);

        MvcResult result = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        // this is done so we can parse LocalDate type fields
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = result.getResponse().getContentAsString();
        UserDTO returnedUser = objectMapper.readValue(json, UserDTO.class);

        Assertions.assertEquals(returnedUser.id(), expedtedUser.id());
        Assertions.assertEquals(returnedUser.firstName(), expedtedUser.firstName());
        Assertions.assertEquals(returnedUser.lastName(), expedtedUser.lastName());
        Assertions.assertEquals(returnedUser.birthdate(), expedtedUser.birthdate());
        Assertions.assertEquals(returnedUser.email(), expedtedUser.email());
    }
}