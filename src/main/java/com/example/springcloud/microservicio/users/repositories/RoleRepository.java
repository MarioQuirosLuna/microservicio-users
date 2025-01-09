package com.example.springcloud.microservicio.users.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.springcloud.microservicio.users.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
