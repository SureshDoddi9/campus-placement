package com.campus.service;

import com.campus.model.Job;
import com.campus.model.Student;
import com.campus.repository.JobRepo;
import com.campus.repository.StudentRepo;
import com.campus.utilities.CampusUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CampusUtility campusUtility;

    @Autowired
    private StudentRepo studentRepo;

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


    public List<Job> getMatchJobs(String studentId){
        Student student =studentRepo.findById(studentId).get();

        List<Job> jobs = jobRepo.findByEducationInIgnoreCaseAndSkillsInIgnoreCaseAndCertificationsInIgnoreCase(
                student.getEducation(),
                student.getSkills(),
                student.getCertifications()
        );
        try {
            Iterator<Job> iterator = jobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.getApplications().contains(student.getId())) {
                    iterator.remove();
                }
            }
        }catch (Exception e){
            log.error(e.toString());
        }
        return jobs;
    }

    public List<Job> getAppliedJobs(String studentId){
        List<Job> jobs = jobRepo.findByApplicationsContains(studentId);
        return jobs;
    }

    public List<Student> getStudents(String jobId){
       Optional<Job> job = jobRepo.findById(jobId);
       List<Student> studentList = new ArrayList<>();
       try {
           if (job.isPresent()) {
               job.get().getApplications().forEach(studentId -> {
                   if(studentRepo.findById(studentId).isPresent()) {
                       studentList.add(studentRepo.findById(studentId).get());
                   }
               });
           }
       }catch (Exception e){
           log.error(e.toString());
       }
       return studentList;
    }
}
