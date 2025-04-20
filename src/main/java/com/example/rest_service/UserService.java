package com.example.rest_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    ObjectMapper objectMapper;

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

    public User userPatch(int id, JsonPatch patch) {
        try {
            User user = getUserById(id);

            JsonNode userNode = objectMapper.convertValue(user, JsonNode.class);

            User patchedUser = objectMapper.treeToValue(patch.apply(userNode), User.class);

            userDAO.save(patchedUser);

            return patchedUser;
        } catch (JsonProcessingException | JsonPatchException e) {
            throw new RuntimeException(e);
        }
    }


}
