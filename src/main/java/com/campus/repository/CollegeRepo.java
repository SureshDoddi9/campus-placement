package com.campus.repository;

import com.campus.model.College;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepo extends MongoRepository<College,String> {
    College findByEmailId(String emailId);
}
