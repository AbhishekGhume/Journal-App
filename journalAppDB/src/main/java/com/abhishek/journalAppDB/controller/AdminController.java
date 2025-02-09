package com.abhishek.journalAppDB.controller;

import com.abhishek.journalAppDB.entity.User;
import com.abhishek.journalAppDB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        List<User> users = userService.getAllUsers();
        if(users!=null || !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addAdmin(@RequestBody User user) {
        userService.addAdmin(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
