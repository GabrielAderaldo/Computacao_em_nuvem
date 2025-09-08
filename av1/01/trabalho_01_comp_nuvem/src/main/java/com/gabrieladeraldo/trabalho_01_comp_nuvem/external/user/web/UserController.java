package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.user.web;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.user.Email;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.user.User;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            User user = convertToEntity(userRequestDTO);
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(convertToDto(savedUser), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private User convertToEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(new Email(userRequestDTO.getEmail()));
        return user;
    }

    private UserResponseDTO convertToDto(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail().getAddress());
    }
}
