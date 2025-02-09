package com.abhishek.journalAppDB.entity;

import com.abhishek.journalAppDB.enums.Sentiment;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

//@Document(collection = "journal_entries")   // if we use this then collection i.e table with "journal_entries" is created
@Document // if we use this then collection with class name is created
@Getter
@Setter
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String Content;
    private LocalDateTime date;
    private Sentiment sentiment;
}
