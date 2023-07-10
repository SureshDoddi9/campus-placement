package com.campus.controller;

import com.campus.dto.CustomResponse;
import com.campus.model.Student;
import com.campus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/uploadData")
    public String uploadStudentData(@RequestParam("file") MultipartFile file,
                                    @RequestParam("sheet") String sheet,
                                    @RequestParam("collegeId") String collegeId){
       return studentService.saveToDb(file,sheet,collegeId);
    }

    @GetMapping("/studentsData")
    public List<Student> studentsData(@RequestParam("collegeId") String collegeId){
        return studentService.fetchAllStudentsByCollege(collegeId);
    }

    @GetMapping("/findStudent")
    public ResponseEntity<Object> getStudent(@RequestParam("id") String id){
        Optional<Student> student = studentService.fetchStudentById(id);
        if (student.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        return ResponseEntity.ok(student.get());
    }

    @PostMapping("/deleteStudent")
    public ResponseEntity<Object> deleteStudent(@RequestParam("id") String id){
        Optional<Student> student = studentService.fetchStudentById(id);
        if (student.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        String status = studentService.deleteStudentById(id);
        return ResponseEntity.ok(new CustomResponse(status,"student deleted successfully..."));
    }


}
