package app.peluargo.user.api;

import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import app.peluargo.user.api.dtos.UserUpdateDTO;

public class UserMapper {
    public static User toUser(UserCreationDTO userCreationDTO) {
        return User.builder()
                .firstName(userCreationDTO.firstName())
                .lastName(userCreationDTO.lastName())
                .birthdate(userCreationDTO.bithdate())
                .email(userCreationDTO.email())
                .build();
    }

    public static User toUser(UserUpdateDTO userUpdateDTO) {
        return User.builder()
                .firstName(userUpdateDTO.firstName())
                .lastName(userUpdateDTO.lastName())
                .birthdate(userUpdateDTO.bithdate())
                .email(userUpdateDTO.email())
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
