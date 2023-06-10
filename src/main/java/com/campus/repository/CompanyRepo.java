package com.campus.repository;

import com.campus.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends MongoRepository<Company,String> {
    Company findByEmailId(String emailId);
}
