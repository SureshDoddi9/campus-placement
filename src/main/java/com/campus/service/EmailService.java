package com.campus.service;

import com.campus.model.ScheduleInterview;
import com.campus.model.Student;
import com.campus.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(body, "text/html");
        };

        mailSender.send(preparator);
    }


    public String notifyByEmail(ScheduleInterview scheduleInterview){
        String status = "failed";
        Optional<Student> student = studentRepo.findById(scheduleInterview.getStudentId());
        if(student.isPresent()){
            String recipient = student.get().getEmailId();
            String subject = "Interview Scheduled!";
            String template = "<html><body> Hello, ${name}!<br><br>"
                    + "We hope you're having a great day!<br><br>"
                    + "This is to inform you that, "
                    + "We have scheduled interview for you on ${date} at ${time}!<br><br>"
                    + "please find the meet link below!<br><br>"
                    +"<a href='" + scheduleInterview.getMeetLink() + "'>" + scheduleInterview.getMeetLink() + "</a><br><br>"
                    + "Best regards,<br>"
                    + "Campus Placement<br>"
                    +"</body></html>";

            template = template.replace("${name}",student.get().getName())
                    .replace("${date}",scheduleInterview.getScheduledDate())
                    .replace("${time}",scheduleInterview.getScheduledTime());

            sendEmail(recipient, subject, template);
            status= "success";
        }
        return status;
    }
}
