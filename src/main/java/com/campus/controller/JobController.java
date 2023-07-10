package com.campus.controller;

import com.campus.dto.CustomResponse;
import com.campus.model.Job;
import com.campus.model.Student;
import com.campus.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/createJob")
    public ResponseEntity<Object> createJob(@RequestBody Job job){
        String status = jobService.createJob(job);
        String message = "job creation failed!";
        if(status.equalsIgnoreCase("success")){
             message = "job created succesfully...";
        }
        return ResponseEntity.ok(new CustomResponse(status,message));
    }

    @PostMapping("/updateJob")
    public ResponseEntity<Object> updateJob(@RequestBody Job job){
        String status = jobService.updateJob(job);
        String message = "job updation failed!";
        if(status.equalsIgnoreCase("success")){
            message = "job updated succesfully...";
        }
        return ResponseEntity.ok(new CustomResponse(status,message));
    }

    @GetMapping("/jobsData")
    public List<Job> jobsData(@RequestParam("orgId") String orgId){
        return jobService.fetchAllJobsByOrganisation(orgId);
    }

    @GetMapping("/findJob")
    public ResponseEntity<Object> getJob(@RequestParam("jobId") String jobId){
        Optional<Job> job = jobService.fetchJobById(jobId);
        if (job.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        return ResponseEntity.ok(job.get());
    }

    @DeleteMapping("/deleteJob")
    public ResponseEntity<Object> deleteJob(@RequestParam("jobId") String jobId){
       String  status =  jobService.deleteJobById(jobId);
       String message = "job deletion failed!";
       if(status.equalsIgnoreCase("success")){
           message = "job deleted succesfully...";
       }
       return ResponseEntity.ok(new CustomResponse(status,message));
    }

    @GetMapping("/matchedJobs")
    public ResponseEntity<Object> matchedJobs(@RequestParam("studentId") String studentId){
        return ResponseEntity.ok(jobService.getMatchJobs(studentId));
    }

    @GetMapping("/appliedJobs")
    public ResponseEntity<Object> appliedJobs(@RequestParam("studentId") String studentId){
        return ResponseEntity.ok(jobService.getAppliedJobs(studentId));
    }

    @GetMapping("/getStudentProfiles")
    public ResponseEntity<Object> getStudentProfiles(@RequestParam("jobId") String jobId){
        return ResponseEntity.ok(jobService.getStudents(jobId));
    }
}
