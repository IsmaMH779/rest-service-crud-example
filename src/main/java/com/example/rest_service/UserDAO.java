package com.example.rest_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
    public void patchMail(@Param("id") int id, @Param("email") String email);
}
