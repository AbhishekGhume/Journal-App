package com.abhishek.journalAppDB.repository;

import com.abhishek.journalAppDB.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate; // we are using MongoTemplate to interact with database

    public List<User> getUsersforSentimentAnalysis() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("username").is("Abhishek"));
////        query.addCriteria(Criteria.where("username").ne("Abhishek"));  // ne = not equal
////        query.addCriteria(Criteria.where(age).lt(20));  // lt = less than
////        query.addCriteria(Criteria.where("username").lte("Abhishek"));  // lte = less than equal to


        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));  // these 2 statements are by default AND
        query.addCriteria(Criteria.where("roles").in("USER", "ADMIN"));   // only the roles having users and admin should be considered

//        if we want OR of 2 statements
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").is(true)));


        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
