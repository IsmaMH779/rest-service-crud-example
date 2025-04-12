package com.example.rest_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

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

    public void patchEmail(int id, Map<String, String> email) {
        userService.updateUserEmail(id, email);
    }
}
