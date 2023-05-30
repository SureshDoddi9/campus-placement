package com.campus.controller;

import com.campus.dto.CustomResponse;
import com.campus.model.User;
import com.campus.service.UserService;
import com.campus.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/signUpCollege")
    public String userSignupCollege(@RequestBody User user){
        userService.saveUser(user);
        return "registered succesfully...";
    }

    @PostMapping("/signUpCompany")
    public String userSignupCompany(@RequestBody User user){
        userService.saveUser(user);
        return "registered succesfully...";
    }

    @GetMapping("/test")
    public String testfunctionality(){
        return "success";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> generateToken(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));
        }catch (Exception e){
//            throw new Exception("Invalid UserName/Password");
            return ResponseEntity.ok(new CustomResponse("login failed","Invalid UserName/Password"));
        }
        return ResponseEntity.ok(jwtUtil.generateToken(user.getEmailId()));
    }
}
