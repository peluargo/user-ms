package app.peluargo.user.api.mappers;

import app.peluargo.user.api.models.User;
import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import app.peluargo.user.api.dtos.UserUpdateDTO;

public class UserMapper {
    public static User toUser(UserCreationDTO userCreationDTO) {
        return User.builder()
                .firstName(userCreationDTO.firstName())
                .lastName(userCreationDTO.lastName())
                .birthdate(userCreationDTO.birthdate())
                .email(userCreationDTO.email())
                .build();
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }
}
