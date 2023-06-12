package com.campus.controller;

import com.campus.dto.CustomResponse;
import com.campus.model.User;
import com.campus.repository.UserRepo;
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

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signUpCollege")
    public ResponseEntity<Object> userSignupCollege(@RequestBody User user){
        String status = userService.saveCollege(user);
        CustomResponse customResponse = new CustomResponse();
        if(status.equalsIgnoreCase("exists")){
            customResponse.setStatus("failed");
            customResponse.setMessage("EmailId already exists...");
        }else {
            customResponse.setStatus("success");
            customResponse.setMessage("registered succesfully...");
        }
        return ResponseEntity.ok(customResponse);
    }

    @PostMapping("/signUpCompany")
    public ResponseEntity<Object> userSignupCompany(@RequestBody User user){
        String status = userService.saveCompany(user);
        CustomResponse customResponse = new CustomResponse();
        if(status.equalsIgnoreCase("exists")){
            customResponse.setStatus("failed");
            customResponse.setMessage("EmailId already exists...");
        }
        customResponse.setStatus("success");
        customResponse.setMessage("registered succesfully...");
        return ResponseEntity.ok(customResponse);
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
            return ResponseEntity.ok(new CustomResponse("login failed","Invalid EmailId/Password"));
        }
        User userData = userRepo.findByEmailId(user.getEmailId());
        String id = userData.getId();
        String role = userData.getRole();
        return ResponseEntity.ok(jwtUtil.generateToken(user.getEmailId(),id,role));
    }
}
