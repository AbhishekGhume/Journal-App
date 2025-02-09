package com.abhishek.journalAppDB.controller;

import com.abhishek.journalAppDB.entity.User;
import com.abhishek.journalAppDB.service.CustomUserDetailsServiceImpl;
import com.abhishek.journalAppDB.service.UserService;
import com.abhishek.journalAppDB.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("health-check")
    public String healthCheck() {
        return "All is well";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.saveNewEntry(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } catch(Exception e) {
            log.error("Exception occurred while creating authentication token ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
