package com.abhishek.journalAppDB.repository;

import com.abhishek.journalAppDB.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    User deleteByUsername(String username);
}
