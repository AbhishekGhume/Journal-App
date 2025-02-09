package com.abhishek.journalAppDB;

import com.abhishek.journalAppDB.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        emailService.sendEmail("ghumeabhi04@gmail.com", "hello", "how are you?");
    }
}
