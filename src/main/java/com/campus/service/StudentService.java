package com.campus.service;

import com.campus.model.Student;
import com.campus.repository.StudentRepo;
import com.campus.utilities.CampusUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CampusUtility campusUtility;

    public String saveToDb(MultipartFile file, String sheetName,String collegeId) {
        String status = "failed";
        try{
            Workbook workbook = campusUtility.getWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);

            List<String> headerList = new LinkedList<>();

            //fetching headers
            Row headerRow = sheet.getRow(0);
            Iterator<Cell> cellIterator = headerRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                headerList.add(cell.getStringCellValue());
            }

            List<Student> studentList = new LinkedList<>();

            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                Student student = new Student();
                Row row = sheet.getRow(j);
                student.setId(campusUtility.generateUniqueId());
                student.setName(row.getCell(0).getStringCellValue());
                student.setRegistrationNumber((long) row.getCell(1).getNumericCellValue());
                student.setAddress(row.getCell(2).getStringCellValue());
                student.setMobileNumber((long) row.getCell(3).getNumericCellValue());
                student.setEmailId(row.getCell(4).getStringCellValue());
                List<String> skills = List.of(row.getCell(5).getStringCellValue().split(","));
                student.setSkills(skills);
                List<String> education = List.of(row.getCell(6).getStringCellValue().split(","));
                student.setEducation(education);
                student.setBatch((long) row.getCell(7).getNumericCellValue());
                List<String> certifications = List.of(row.getCell(8).getStringCellValue().split(","));
                student.setCertifications(certifications);
                student.setCollegeId(collegeId);
                studentList.add(student);

                }
            List<Student> savedStudents = studentRepo.saveAll(studentList);
            if(!savedStudents.isEmpty()){
               status ="success";
            }

        }catch (Exception e){
            log.error("Error : "+e);
        }

        return status;
    }

    public List<Student> fetchAllStudentsByCollege(String collegeId){
        return studentRepo.findByCollegeId(collegeId);
    }

    public Optional<Student> fetchStudentById(String id){
         return  studentRepo.findById(id);
    }

}
