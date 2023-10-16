package com.uploadimage.uploadimage.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uploadimage.uploadimage.helper.ImageUploadHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class FileUploadController {

    @Autowired
    private ImageUploadHelper imageUploadHelper;

    @PostMapping("/uplaod-image")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Please select any file");
        }
        // if (!multipartFile.getContentType().equals("image/png")) {
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PNG files
        // acceptable only");
        // }

        boolean uploadFile = imageUploadHelper.uploadFile(multipartFile);
        if (uploadFile)
            return ResponseEntity.ok("File Uploaded...");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");

    }
}
