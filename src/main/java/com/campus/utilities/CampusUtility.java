package com.campus.utilities;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
public class CampusUtility {

    public String generateUniqueId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public Workbook getWorkbook(MultipartFile file){
        Workbook workbook = null;
        try {
            InputStream inputStream = file.getInputStream();

            // Create a workbook from the input stream
            workbook = WorkbookFactory.create(inputStream);

        }catch (Exception e){
            log.error("Error : "+e);
        }
        return workbook;
    }
}
