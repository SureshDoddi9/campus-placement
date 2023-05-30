package com.campus.service;

import com.campus.model.User;
import com.campus.repository.UserRepo;
import com.campus.utilities.CampusUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private CampusUtility campusUtility;

    public void saveUser(User user){
        user.setId(campusUtility.generateUniqueId());
        userRepo.save(user);
    }
}
