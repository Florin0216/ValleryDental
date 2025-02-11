package com.example.vallerydental.repository;

import com.example.vallerydental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Dictionary;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}
