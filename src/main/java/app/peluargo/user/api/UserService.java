package app.peluargo.user.api;

import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import app.peluargo.user.api.dtos.UserUpdateDTO;
import app.peluargo.user.api.exceptions.InvalidUserEmailException;
import app.peluargo.user.api.exceptions.UserEmailIsNotAvailableException;
import app.peluargo.user.api.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserCreationDTO userCreationDTO) {
        if (this.isEmailUnavailable(userCreationDTO.email())) {
            throw new UserEmailIsNotAvailableException();
        }

        User createdUser = this.userRepository.save(UserMapper.toUser(userCreationDTO));

        return UserMapper.toUserDTO(createdUser);
    }

    public Page<UserDTO> searchAll(Pageable pageable) {
        return this.userRepository.findAll(pageable).map(UserMapper::toUserDTO);
    }

    public UserDTO searchOne(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserMapper.toUserDTO(user);
    }

    public UserDTO update(UUID id, UserUpdateDTO userUpdateDTO) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (userUpdateDTO.firstName() != null) {
            user.setFirstName(userUpdateDTO.firstName());
        }

        if (userUpdateDTO.lastName() != null) {
            user.setLastName(userUpdateDTO.lastName());
        }

        if (userUpdateDTO.birthdate() != null) {
            user.setBirthdate(userUpdateDTO.birthdate());
        }

        if (userUpdateDTO.email() != null) {
            if (this.isEmailUnavailable(userUpdateDTO.email())) {
                throw new UserEmailIsNotAvailableException();
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
