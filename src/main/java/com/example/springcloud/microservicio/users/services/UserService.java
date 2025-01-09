package com.example.springcloud.microservicio.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springcloud.microservicio.users.entities.User;
import com.example.springcloud.microservicio.users.repositories.UserRepository;

@Service
public class UserService implements IUserService{
    
    @Autowired
    private UserRepository repository;

    @Transactional
    public User save(User user){
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll(){
        return repository.findAll();
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
}
