package com.campus.controller;

import com.campus.dto.CustomResponse;
import com.campus.model.Company;
import com.campus.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;


    @PostMapping("/updateCompany")
    public ResponseEntity<Object> updateCompany(@RequestBody Company company){
        String status = companyService.updateCompany(company);
        String message = "company updation failed!";
        if(status.equalsIgnoreCase("success")){
            message = "company data updated succesfully...";
        }
        return ResponseEntity.ok(new CustomResponse(status,message));
    }
    @GetMapping("/getCompanyById")
    public ResponseEntity<Object> getCompany(@RequestParam("id") String id){
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse("failed","Company Not found"));
        }
        return ResponseEntity.ok(company.get());
    }

    @GetMapping("/getCompanyByEmailId")
    public ResponseEntity<Object> getCollegeEmailId(@RequestParam("emailId") String emailId){
        Company company = companyService.getCompanyByEmail(emailId);
        if (company==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse("failed","College Not found"));
        }
        return ResponseEntity.ok(company);
    }
}
