package com.hoopstyles.hoopstyles.services;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.criteria.Path;

@Service
public class FileService {
    
    public void saveFile( MultipartFile file, String uploadPath) {
        try {
            byte[] bytes = file.getBytes();
            java.nio.file.Path path = Paths.get(uploadPath + file.getOriginalFilename());
            java.nio.file.Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

}
