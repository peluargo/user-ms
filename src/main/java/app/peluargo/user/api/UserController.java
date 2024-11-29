package app.peluargo.user.api;

import app.peluargo.user.api.dtos.UserCreationDTO;
import app.peluargo.user.api.dtos.UserDTO;
import app.peluargo.user.api.dtos.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserCreationDTO userCreationDTO) {
        UserDTO userDTO = this.userService.create(userCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> searchOne(@RequestParam UUID id) {
        UserDTO userDTO = this.userService.searchOne(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestParam UUID id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO userDTO = this.userService.update(id, userUpdateDTO);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
