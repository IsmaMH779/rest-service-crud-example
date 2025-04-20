package com.example.rest_service;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/api/v0/users";

    @Autowired
    UserController userController;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userController.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        return ResponseEntity.ok().body(userController.getUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> removeUser(@PathVariable int id) {
        UserDTO u = userController.getUser(id);
        userController.removeUser(id);
        return ResponseEntity.ok().body(u);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        userController.addNewUser(userDTO);
        return ResponseEntity.ok().body("Usuario añadido con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        userController.updateUser(id, userDTO);
        return ResponseEntity.ok().body("Usuario con id " + id + " actualizado con exito");
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchEmail(@PathVariable int id, @RequestBody JsonPatch patch) {
        return ResponseEntity.ok().body(userController.userPatch(id, patch));
    }
}
