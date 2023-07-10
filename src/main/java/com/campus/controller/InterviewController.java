package com.campus.controller;

import com.campus.dto.CustomResponse;
import com.campus.model.ScheduleInterview;
import com.campus.service.EmailService;
import com.campus.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    InterviewService interviewService;

    @Autowired
    EmailService emailService;

    @PostMapping("/scheduleInterview")
    public ResponseEntity<Object> scheduleInterview(@RequestBody ScheduleInterview scheduleInterview){
         String status = interviewService.saveInterview(scheduleInterview);
         emailService.notifyByEmail(scheduleInterview);
         return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(status,"Interview scheduled succesfully..."));
    }
}
