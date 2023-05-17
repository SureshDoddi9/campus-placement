package com.campus.controller;

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
    public String uploadStudentData(@RequestParam("file") MultipartFile file, @RequestParam("sheet") String sheet){
       return studentService.saveToDb(file,sheet);
    }

    @GetMapping("/studentData")
    public List<Student> studentData(){
        return studentService.fetchAllStudents();
    }

    @GetMapping("/findStudent")
    public ResponseEntity<Object> getStudent(@RequestParam("id") String id){
        Optional<Student> student = studentService.fetchStudentById(id);
        if (student.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        return ResponseEntity.ok(student.get());
    }
}
