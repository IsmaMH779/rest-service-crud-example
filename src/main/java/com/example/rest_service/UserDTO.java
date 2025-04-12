package com.example.rest_service;

public class UserDTO {
    int id;
    String email;
    String fullName;
    String password;

    public UserDTO() {}

    public UserDTO(int id, String email, String fullName, String password) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public UserDTO(Object o) {
        if (o instanceof User user) {
            this.id = user.id;
            this.email = user.getEmail();
            this.fullName = user.getFullname();
            this.password = user.getPassword();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
