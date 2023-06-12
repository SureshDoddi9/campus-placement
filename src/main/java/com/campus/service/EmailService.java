package com.campus.service;

import com.campus.model.ScheduleInterview;
import com.campus.model.Student;
import com.campus.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StudentRepo studentRepo;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }


//    public String notifyByEmail(ScheduleInterview scheduleInterview){
//        String recipient = "john.doe@example.com";
//        String subject = "Interview Scheduled!";
//        String template = "Hello, ${firstName}!\n\n"
//                + "This is a message just for you, ${firstName} ${lastName}. "
//                + "We hope you're having a great day!\n\n"
//                + "Best regards,\n"
//                + "The Spring Boot Team";
//        Optional<Student> student = studentRepo.findById(scheduleInterview.getStudentId());
//
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("firstName", "John");
//        variables.put("lastName", "Doe");
//        template = template.replace("${firstName}",student.get().getName());
//
//        sendEmail(recipient, subject, template);
//    }
}
