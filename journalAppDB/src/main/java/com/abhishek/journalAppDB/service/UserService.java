package com.abhishek.journalAppDB.service;

import com.abhishek.journalAppDB.entity.JournalEntry;
import com.abhishek.journalAppDB.entity.User;
import com.abhishek.journalAppDB.repository.JournalEntryRepository;
import com.abhishek.journalAppDB.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j // printing logs using slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private final static Logger logger = LoggerFactory.getLogger(UserService.class);  // printing logs without using slf4j


    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public void saveNewEntry(User user) {
        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e) {
            // without slf4j
//            logger.error("Error occured for: {}",user.getUsername(), e);
//            logger.warn("ksndisfsdm");
//            logger.info("ksndisfsdm");
//            logger.debug("ksndisfsdm");   // debug is by default off
//            logger.trace("ksndisfsdm");    // trace is by default off
//            throw new RuntimeException("An error occurred while saving the user", e);

            // with slf4j
            log.error("Error occured for: {}",user.getUsername(), e);
            log.debug("Debugging for: {}",user.getUsername(), e);
        }
    }

    public Optional<User> getById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId myId) {
        userRepository.deleteById(myId);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addAdmin(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }
}


//  controller  --> service --> repository