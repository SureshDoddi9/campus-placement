package com.campus.service;

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

    public String saveUser(User user){
        String status = "success";
        User userCheck = userRepo.findByEmailId(user.getEmailId());
        if(userCheck.getEmailId().equalsIgnoreCase(user.getEmailId())){
            status ="exists";
        }else{
            user.setId(campusUtility.generateUniqueId());
            userRepo.save(user);
        }
        return status;
    }
}
