package com.campus.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scheduleInterviews")
@Data
public class ScheduleInterview {

    @Id
    private String id;
    private String studentId;
    private String jobId;
    private String scheduledTime;
    private String scheduledDate;
    private String meetLink;
}
