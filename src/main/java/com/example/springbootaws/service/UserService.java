package com.example.springbootaws.service;


import com.example.springbootaws.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
   Page<User> getAllUsers(Integer pagenumber, Integer Pagesize);

    Optional<User> getUserById(Long id);

    String saveUser(User user);

    String deleteUser(Long id);

   String updateUser(User existingUser, User user);
}
