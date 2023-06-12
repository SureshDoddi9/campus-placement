package com.campus.repository;

import com.campus.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends MongoRepository<Job,String> {
    List<Job> findByOrganisationId(String orgId);

    List<Job> findByEducationInIgnoreCaseAndSkillsInIgnoreCaseAndCertificationsInIgnoreCase(
            List<String> education,
            List<String> skills,
            List<String> certifications);
}
