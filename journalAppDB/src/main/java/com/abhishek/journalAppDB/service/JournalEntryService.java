package com.abhishek.journalAppDB.service;

import com.abhishek.journalAppDB.entity.JournalEntry;
import com.abhishek.journalAppDB.entity.User;
import com.abhishek.journalAppDB.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service // we can use @Component also
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch(Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getall() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId myId, String username) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));

            if(removed) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(myId);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting entry.", e);
        }
        return removed;
    }

}


//  controller  --> service --> repository