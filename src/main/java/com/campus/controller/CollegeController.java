package com.campus.controller;

import com.campus.dto.CustomResponse;
import com.campus.model.College;
import com.campus.model.Job;
import com.campus.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    CollegeService collegeService;


    @PostMapping("/updateCollege")
    public ResponseEntity<Object> updateCollege(@RequestBody College college){
        String status = collegeService.updateCollege(college);
        String message = "college updation failed!";
        if(status.equalsIgnoreCase("success")){
            message = "college data updated succesfully...";
        }
        return ResponseEntity.ok(new CustomResponse(status,message));
    }
    @GetMapping("/getCollegeById")
    public ResponseEntity<Object> getCollege(@RequestParam("id") String id){
        Optional<College> college = collegeService.getCollegeById(id);
        if (college.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse("failed","College Not found"));
        }
        return ResponseEntity.ok(college.get());
    }

    @GetMapping("/getCollegeByEmailId")
    public ResponseEntity<Object> getCollegeEmailId(@RequestParam("emailId") String emailId){
        College college = collegeService.getCollegeByEmail(emailId);
        if (college==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse("failed","College Not found"));
        }
        return ResponseEntity.ok(college);
    }

}
