package com.campus.service;

import com.campus.model.ScheduleInterview;
import com.campus.repository.ScheduleInterviewRepo;
import com.campus.utilities.CampusUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {

    @Autowired
    ScheduleInterviewRepo scheduleInterviewRepo;

    @Autowired
    CampusUtility campusUtility;

    public String saveInterview(ScheduleInterview scheduleInterview){
        scheduleInterview.setId(campusUtility.generateUniqueId());
        ScheduleInterview savedData =  scheduleInterviewRepo.save(scheduleInterview);
        return "success";
    }
}
