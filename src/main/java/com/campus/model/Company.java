package com.campus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "companies")
public class Company {
    @Id
    private String id;
    private String companyName;
    private String description;
    private String yearFounded;
    private String companyWebsiteLink;
    private String phoneNumber;
    private String address;
    private String emailId;
    private String employesCount;
    private String primaryConatactPerson;
    private String primaryContactNumber;
}
