package app.peluargo.user.api;

import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import app.peluargo.user.api.dtos.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<UserDTO>> searchAll(Pageable pageable) {
        Page<UserDTO> users = this.userService.searchAll(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> searchOne(@PathVariable("id") UUID id) {
        UserDTO userDTO = this.userService.searchOne(id);
        return ResponseEntity.ok(userDTO);
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
