package com.campus.repository;

import com.campus.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User,Integer> {
//    User findByUserName(String username);
    User findByEmailId(String emailId);
}
