package app.peluargo.user.api.services;

import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import app.peluargo.user.api.dtos.UserUpdateDTO;
import app.peluargo.user.api.models.User;
import app.peluargo.user.api.exceptions.UserEmailIsNotAvailableException;
import app.peluargo.user.api.exceptions.UserNotFoundException;
import app.peluargo.user.api.mappers.UserMapper;
import app.peluargo.user.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserCreationDTO userCreationDTO) {
        if (this.emailIsUnavailable(userCreationDTO.email())) {
            throw new UserEmailIsNotAvailableException(userCreationDTO.email());
        }

        User createdUser = this.userRepository.save(UserMapper.toUser(userCreationDTO));

        return UserMapper.toUserDTO(createdUser);
    }

    public Page<UserDTO> searchAll(Pageable pageable, List<UUID> ids) {
        if (ids != null && !ids.isEmpty()) {
            return this.userRepository.findAllByIdIn(pageable, ids).map(UserMapper::toUserDTO);
        }

        return this.userRepository.findAll(pageable).map(UserMapper::toUserDTO);
    }

    public UserDTO searchOne(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toUserDTO(user);
    }

    public UserDTO update(UUID id, UserUpdateDTO userUpdateDTO) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

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
            if (this.emailIsUnavailable(userUpdateDTO.email())) {
                throw new UserEmailIsNotAvailableException(userUpdateDTO.email());
            }

            user.setEmail(userUpdateDTO.email());
        }

        User updatedUser = this.userRepository.save(user);

        return UserMapper.toUserDTO(updatedUser);
    }

    public void delete(UUID id) {
        this.userRepository.deleteById(id);
    }

    public boolean emailIsUnavailable(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
