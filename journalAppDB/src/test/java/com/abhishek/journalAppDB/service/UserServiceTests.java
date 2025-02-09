package com.abhishek.journalAppDB.service;

import com.abhishek.journalAppDB.entity.User;
import com.abhishek.journalAppDB.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void testFindByUsername() {
        User user = userService.findByUsername("Abhishek");
//        assertNotNull(userRepository.findByUsername("Abhishek"));   // testing username in database is not equals to null
        assertFalse(user.getJournalEntries().isEmpty());         // testing list for journal entries for any particular user should not be empty
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Abhishek",
            "Shubham",
            "Admin"
    })
    public void testUserExistance(String name) {
        assertNotNull(userService.findByUsername(name));   // testing username in database is not equals to null
    }

    @ParameterizedTest
    @CsvSource({
           "1,2,3",
           "2,3,4",
           "5,4,9"
    })
    public void test(int a, int b, int ans) {
        assertEquals(ans, a+b);
    }


}
