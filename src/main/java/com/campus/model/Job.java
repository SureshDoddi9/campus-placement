package com.campus.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "job")
@Data
public class Job {

    @Id
    private String id;
    private String organisationId;
    private String title;
    private String description;
    private String location;
    private List<String> skills;
    private List<String> education;
    private long batch;
    private List<String> certifications;
    private List<String> applications;
}
