package app.peluargo.user.api.controllers;

import app.peluargo.user.api.commons.dtos.ApiResponseDTO;
import app.peluargo.user.api.commons.mappers.ApiResponseMapper;
import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import app.peluargo.user.api.dtos.UserUpdateDTO;
import app.peluargo.user.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping
    public ResponseEntity<ApiResponseDTO<UserDTO>> create(@RequestBody UserCreationDTO userCreationDTO) {
        UserDTO userDTO = this.userService.create(userCreationDTO);
        ApiResponseDTO<UserDTO> body = ApiResponseMapper.toApiResponse(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserDTO>> searchOne(@PathVariable("id") UUID id) {
        UserDTO userDTO = this.userService.searchOne(id);
        ApiResponseDTO<UserDTO> body = ApiResponseMapper.toApiResponse(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<UserDTO>>> searchAll(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) List<UUID> ids
    ) {
        Page<UserDTO> users = this.userService.searchAll(pageable, ids);
        ApiResponseDTO<Page<UserDTO>> body = ApiResponseMapper.toApiResponse(users);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserDTO>> update(
            @PathVariable("id") UUID id,
            @RequestBody UserUpdateDTO userUpdateDTO
    ) {
        UserDTO userDTO = this.userService.update(id, userUpdateDTO);
        ApiResponseDTO<UserDTO> body = ApiResponseMapper.toApiResponse(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        this.userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
