package com.lemonde.web.services;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUpload {

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {

        Cloudinary cloudinary = new Cloudinary("cloudinary://134749123623781:AVj41FKcb6aIvjLUpPuJpbBHDVk@dt8ktufup");
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        new HashMap<String, String>() {
                            {
                                put("public_id", UUID.randomUUID().toString());
                            }
                        })
                .get("url")
                .toString();
    }
}