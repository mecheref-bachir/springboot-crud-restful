package com.example.springbootaws.service;

import com.example.springbootaws.domain.User;
import com.example.springbootaws.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserServiceImpl implements   UserService{

private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAllUsers(Integer pagenumber, Integer pagesize) {
        Pageable pageable = PageRequest.of(pagenumber,pagesize);
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public String saveUser(User user) {
        userRepository.save(user);
        return "user with id "+ user.getId()  + " saved successfully";
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "user with id "+ id  + " deleted successfully";
    }

    @Override
    public String updateUser(User existingUser, User user) {
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        userRepository.save(existingUser);
        return "user with id "+ existingUser.getId()  + " updated successfully";
    }
}
