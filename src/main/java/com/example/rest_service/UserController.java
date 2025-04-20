package com.example.rest_service;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    public List<UserDTO> getAllUsers() {
        List users = userService.getUsers();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO getUser(int id) {
        return new UserDTO(userService.getUserById(id));
    }

    public void addNewUser(UserDTO userDTO) {
        userService.newUser(userDTO);
    }

    public void removeUser(int id) {
        userService.deleteUser(id);

    }

    public void updateUser(int id, UserDTO userDTO) {
        userService.updateUser(id, userDTO);
    }

    public User userPatch(int id, JsonPatch patch) {
        return userService.userPatch(id, patch);
    }
}
