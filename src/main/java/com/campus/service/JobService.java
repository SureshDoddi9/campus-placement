package com.campus.service;

import com.campus.model.Job;
import com.campus.model.Student;
import com.campus.repository.JobRepo;
import com.campus.utilities.CampusUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CampusUtility campusUtility;

    public String createJob(Job job){
        String status = "failed";
        job.setId(campusUtility.generateUniqueId());
        Job savedJob = jobRepo.insert(job);
        if(savedJob.getId()!=null){
            status ="success";
        }
        return status;
    }

    public String updateJob(Job job){
        String status = "failed";
        Job savedJob = jobRepo.save(job);
        if(!savedJob.getId().isEmpty()){
            status ="success";
        }
        return status;
    }

    public List<Job> fetchAllJobsByOrganisation(String orgId){
          return jobRepo.findByOrganisationId(orgId);
    }

    public Optional<Job> fetchJobById(String jobId){
        return jobRepo.findById(jobId);
    }

    public String deleteJobById(String jobId){
        String status = "failed";
        try {
            jobRepo.deleteById(jobId);
            status = "success";
        }catch (Exception e){
            log.error("Error : "+e);
        }
        return status;
    }
}
