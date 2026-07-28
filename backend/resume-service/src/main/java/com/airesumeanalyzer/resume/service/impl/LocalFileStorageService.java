package com.airesumeanalyzer.resume.service.impl;

import com.airesumeanalyzer.resume.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService {

    // Upload folder path
    private static final String UPLOAD_DIR = "uploads";

    @Override
    public String storeFile(MultipartFile file) {

        try {

            // Validate file
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("Please select a file.");
            }

            // Original filename
            String originalFileName =
                    StringUtils.cleanPath(file.getOriginalFilename());

            // File extension
            String extension =
                    originalFileName.substring(originalFileName.lastIndexOf("."));

            // Allow only PDF & DOCX
            if (!extension.equalsIgnoreCase(".pdf")
                    && !extension.equalsIgnoreCase(".docx")) {

                throw new RuntimeException(
                        "Only PDF and DOCX files are allowed."
                );
            }

            // Create uploads directory
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Unique filename
            String fileName =
                    UUID.randomUUID() + extension;

            // Save file
            Path targetLocation =
                    uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return targetLocation.toString();

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Could not store file.",
                    ex
            );
        }
    }

    @Override
    public void deleteFile(String filePath) {

        try {

            Files.deleteIfExists(Paths.get(filePath));

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Could not delete file.",
                    ex
            );
        }
    }
}