package com.abhishek.journalAppDB.Scheduler;

import com.abhishek.journalAppDB.entity.JournalEntry;
import com.abhishek.journalAppDB.entity.User;
import com.abhishek.journalAppDB.enums.Sentiment;
import com.abhishek.journalAppDB.repository.UserRepositoryImpl;
import com.abhishek.journalAppDB.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 3 * * SUN", zone = "Asia/Kolkata")  // cron expression = sec min hr date month day year
    public void fetchUsersAndSendSAMail() {
        List<User> users = userRepository.getUsersforSentimentAnalysis();
        for(User user: users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> filteredJournalEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> map = new HashMap<>();
            for(Sentiment sentiment: filteredJournalEntries) {
                if(sentiment != null) {
                    map.put(sentiment, map.getOrDefault(sentiment, 0)+1);
                }
            }

            Sentiment mostFrequent = null;
            int maxCnt = 0;
            for(Map.Entry<Sentiment, Integer> entry: map.entrySet()) {
                if(entry.getValue() > maxCnt) {
                    maxCnt = entry.getValue();
                    mostFrequent = entry.getKey();
                }
            }

            if(mostFrequent != null)
                emailService.sendEmail(user.getEmail(), "Sentiment for 7 days", mostFrequent.toString());
        }
    }
}
