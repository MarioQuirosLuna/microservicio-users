package com.example.springcloud.microservicio.users.services;

import java.util.Optional;

import com.example.springcloud.microservicio.users.entities.User;

public interface IUserService {

    User save(User user);
    Optional<User> findById(Long id);
    User findByUsername(String username);
    Iterable<User> findAll();
    void delete(Long id);

}