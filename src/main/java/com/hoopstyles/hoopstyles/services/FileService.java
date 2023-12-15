package com.hoopstyles.hoopstyles.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public String uploadPath = "src/main/resources/static/images/";
    
    public void saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadPath, fileName);

            // Verificar y crear el directorio si no existe
            Files.createDirectories(path.getParent());

            // Escribir el archivo
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
