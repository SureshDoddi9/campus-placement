package com.campus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "colleges")
public class College {
    @Id
    private String id;
    private String collegeName;
    private String emailId;
    private String address;
    private String phoneNumber;
    private String alternateNumber;
    private String collegeWebsiteLink;
}
