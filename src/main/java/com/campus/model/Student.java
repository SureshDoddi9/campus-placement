package com.campus.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "student")
@Data
public class Student {
    @Id
    private String id;
    private String name;
    private long registrationNumber;
    private String address;
    private long mobileNumber;
    private String emailId;
    private List<String> skills;
    private List<String> education;
    private long batch;
    private List<String> certifications;
}
