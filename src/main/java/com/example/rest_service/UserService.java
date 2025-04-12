package com.example.rest_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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

    public void updateUser(int id, UserDTO userDTO) {
        User user = getUserById(id);

        if (userDTO.email != null) {
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.fullName != null) {
            user.setFullname(userDTO.getFullName());
        }

        if (userDTO.password != null) {
            user.setPassword(userDTO.getPassword());
        }

        userDAO.save(user);
    }

    @Transactional
    public void updateUserEmail(int id, Map<String, String> email) {
        userDAO.patchMail(id, email.get("email"));
    }
}
