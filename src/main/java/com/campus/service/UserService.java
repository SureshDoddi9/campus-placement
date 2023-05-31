package com.campus.service;

import com.campus.model.College;
import com.campus.model.User;
import com.campus.repository.UserRepo;
import com.campus.utilities.CampusUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private CampusUtility campusUtility;

    @Autowired
    private CollegeService collegeService;

    public String saveUser(User user){
        String status = "success";
        User userCheck = userRepo.findByEmailId(user.getEmailId());
        if(userCheck!=null){
            status ="exists";
        }else{
            String id = campusUtility.generateUniqueId();
            user.setId(id);
            userRepo.save(user);
            College college = College.builder()
                    .id(id)
                    .emailId(user.getEmailId()).build();
            collegeService.updateCollege(college);
        }
        return status;
    }
}
