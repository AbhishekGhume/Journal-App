package com.abhishek.journalAppDB.service;

import com.abhishek.journalAppDB.config.SpringSecurity;
import com.abhishek.journalAppDB.entity.User;
import com.abhishek.journalAppDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service // we can use @Component also
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);   // this is User from my entity class
        if(user!=null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().
                    username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();

            return  userDetails;
        }
        throw new UsernameNotFoundException("User not found with: "+username);
    }
}
