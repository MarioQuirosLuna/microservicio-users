package com.example.springcloud.microservicio.users.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.springcloud.microservicio.users.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
