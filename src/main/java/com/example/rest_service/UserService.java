package com.example.rest_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public List<User> getUsers() {

        return userDAO.findAll();
    }

    public User getUserById(int id) {
        Optional<User> u = userDAO.findById(id);


        return u.isPresent() ? u.get() : null;
    }

    public void newUser(UserDTO userDTO) {
        User user = new User(userDTO.getEmail(),userDTO.getFullName(), userDTO.getPassword());

        userDAO.save(user);
    }

    public void deleteUser(int id) {
        userDAO.deleteById(id);
    }
}
