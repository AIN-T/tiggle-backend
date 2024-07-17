package com.gamja.tiggle.program.adapter.out.cloud;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.example.tiggle.program.application.port.out.S3UploadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3ImageUploadAdapter implements S3UploadPort {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    public String makeFolder() {
        String folderPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        File uploadPathFolder = new File(folderPath);
        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }

    @Override
    public List<String> uploadProductImages(MultipartFile[] files) {
        List<String> uploadFilePaths = new ArrayList<>();

        for (MultipartFile file : files) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            String uploadPath = makeFolder();
            try {
                String fileName = uploadPath + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
                amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);

                uploadFilePaths.add("https://daqu2024-s3.s3.ap-northeast-2.amazonaws.com/" + fileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return uploadFilePaths;
    }
}
