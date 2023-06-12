package com.campus.repository;

import com.campus.model.ScheduleInterview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleInterviewRepo extends MongoRepository<ScheduleInterview,String> {
}

