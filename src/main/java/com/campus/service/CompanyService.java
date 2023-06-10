package com.campus.service;

import com.campus.model.College;
import com.campus.model.Company;
import com.campus.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepo companyRepo;
    public String updateCompany(Company company){
        String status = "failed";
        Company savedCompany = companyRepo.save(company);
        if(savedCompany!=null){
            status = "success";
        }
        return status;
    }

    public Company getCompanyByEmail(String emailId){
        return companyRepo.findByEmailId(emailId);
    }

    public Optional<Company> getCompanyById(String id){
        return companyRepo.findById(id);
    }
}
