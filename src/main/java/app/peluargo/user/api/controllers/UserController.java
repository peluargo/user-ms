package app.peluargo.user.api.controllers;

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
    public ResponseEntity<UserDTO> create(@RequestBody UserCreationDTO userCreationDTO) {
        UserDTO userDTO = this.userService.create(userCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> searchOne(@PathVariable("id") UUID id) {
        UserDTO userDTO = this.userService.searchOne(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> searchAll(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) List<UUID> ids
    ) {
        Page<UserDTO> users = this.userService.searchAll(pageable, ids);
        return ResponseEntity.ok(users);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") UUID id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO userDTO = this.userService.update(id, userUpdateDTO);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
