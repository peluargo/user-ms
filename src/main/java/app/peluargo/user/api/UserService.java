package app.peluargo.user.api;

import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import app.peluargo.user.api.dtos.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserCreationDTO userCreationDTO) {
        if (this.isEmailUnavailable(userCreationDTO.email())) {
            throw new RuntimeException("Email is not available");
        }

        User createdUser = this.userRepository.save(UserMapper.toUser(userCreationDTO));

        return UserMapper.toUserDTO(createdUser);
    }

    public UserDTO searchOne(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow();

        return UserMapper.toUserDTO(user);
    }

    public UserDTO update(UUID id, UserUpdateDTO userUpdateDTO) {
        User user = this.userRepository.findById(id).orElseThrow();

        if (userUpdateDTO.firstName() != null) {
            user.setFirstName(userUpdateDTO.firstName());
        }

        if (userUpdateDTO.lastName() != null) {
            user.setLastName(userUpdateDTO.lastName());
        }

        if (userUpdateDTO.bithdate() != null) {
            user.setBirthdate(userUpdateDTO.bithdate());
        }

        if (userUpdateDTO.email() != null) {
            if (this.isEmailUnavailable(userUpdateDTO.email())) {
                throw new RuntimeException("Email is not available");
            }

            user.setEmail(userUpdateDTO.email());
        }

        User updatedUser = this.userRepository.save(user);

        return UserMapper.toUserDTO(updatedUser);
    }

    public void delete(UUID id) {
        this.userRepository.deleteById(id);
    }

    public boolean isEmailUnavailable(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
