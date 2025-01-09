package com.example.springcloud.microservicio.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springcloud.microservicio.users.entities.Role;
import com.example.springcloud.microservicio.users.entities.User;
import com.example.springcloud.microservicio.users.repositories.RoleRepository;
import com.example.springcloud.microservicio.users.repositories.UserRepository;

@Service
public class UserService implements IUserService{
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(getRoles(user));
        user.setEnabled(true);

        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username){
        return Optional.of(repository.findByUsername(username));
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll(){
        return repository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> update(User user, Long id) {
        
        Optional<User> userOptional = this.findById(id);
        
        return userOptional.map(userDB -> {
            userDB.setEmail(user.getEmail());
            userDB.setUsername(user.getUsername());
            if(user.isEnabled() == null){
                userDB.setEnabled(true);
            }else{
                userDB.setEnabled(user.isEnabled());
            }
            userDB.setRoles(getRoles(user));

            return Optional.of(repository.save(userDB));
        }).orElseGet(() -> Optional.empty());


    }
    
    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private List<Role> getRoles(User user) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        roleOptional.ifPresent(role -> roles.add(role));

        if(user.isAdmin()){
            Optional<Role> adminRoleOptional = roleRepository.findByName("ROLE_ADMIN");
            adminRoleOptional.ifPresent(roles::add);
        }
        return roles;
    }
}
