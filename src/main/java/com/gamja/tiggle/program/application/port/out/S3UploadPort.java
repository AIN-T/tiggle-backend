package com.gamja.tiggle.program.application.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3UploadPort {
    List<String> uploadProductImages(MultipartFile[] files);
}
