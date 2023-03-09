package com.example.springbootaws.controller;

import com.example.springbootaws.domain.User;
import com.example.springbootaws.exeption.CustomResourceNotFoundException;
import com.example.springbootaws.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{pagenumber}/{pagesize}")
    public ResponseEntity<Page<User>> getAllUsers(@PathVariable Integer pagenumber,@PathVariable Integer pagesize) {
        return ResponseEntity.ok(userService.getAllUsers(pagenumber,pagesize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
    User user = userService.getUserById(id).orElseThrow(()->new CustomResourceNotFoundException("User with id " + id + "not found"));
        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        User user = userService.getUserById(id).orElseThrow(()->new CustomResourceNotFoundException("User with id " + id + "not found"));
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id ,@RequestBody User user){
        User existingUser = userService.getUserById(id).orElseThrow(()->new CustomResourceNotFoundException("User with id " + id + "not found"));
      return  ResponseEntity.ok(userService.updateUser(existingUser, user));
    }

}
