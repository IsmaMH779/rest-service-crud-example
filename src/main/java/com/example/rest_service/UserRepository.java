package com.example.rest_service;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    List<UserDTO> userDTOS = new ArrayList<>();

    public UserRepository() {
        userDTOS.add(new UserDTO(1, "joan@gmail.com","joan un", "111"));
        userDTOS.add(new UserDTO(2, "joan@gmail.com","joan dos", "222"));
        userDTOS.add(new UserDTO(3, "joan@gmail.com","joan tres", "333"));
    }

    public List<UserDTO> getUsers() {
        return userDTOS;
    }

    public UserDTO getUserById(int id) {
        Optional<UserDTO> u =  userDTOS.stream().filter(user -> user.getId() == id).findFirst();

        if (u.isPresent()) {
            return u.get();
        }
        return null;
    }

    public void save(UserDTO userDTO) {
        userDTOS.add(userDTO);
    }

    public void delete(int id) {
        userDTOS.removeIf(user -> user.getId() == id);
    }
}
