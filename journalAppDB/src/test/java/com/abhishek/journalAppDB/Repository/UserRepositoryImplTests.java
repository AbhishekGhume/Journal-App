package com.abhishek.journalAppDB.Repository;

import com.abhishek.journalAppDB.repository.UserRepositoryImpl;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserRepositoryImplTests {
    @Autowired
    private UserRepositoryImpl userRepository;
    @Test
    public void testGetUsersforSentimentAnalysis() {
        Assertions.assertNotNull(userRepository.getUsersforSentimentAnalysis());
    }
}
