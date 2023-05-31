package com.campus.service;

import com.campus.model.College;
import com.campus.repository.CollegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollegeService {

    @Autowired
    CollegeRepo collegeRepo;
    public String updateCollege(College college){
        String status = "failed";
        College savedCollege = collegeRepo.save(college);
        if(savedCollege!=null){
         status = "success";
       }
       return status;
    }

    public College getCollegeByEmail(String emailId){
        return collegeRepo.findByEmailId(emailId);
    }

    public Optional<College> getCollegeById(String id){
        return collegeRepo.findById(id);
    }
}
