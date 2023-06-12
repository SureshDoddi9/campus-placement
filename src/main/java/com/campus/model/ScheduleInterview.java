package com.campus.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

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
