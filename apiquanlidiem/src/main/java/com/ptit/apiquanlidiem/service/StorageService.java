package com.ptit.apiquanlidiem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public interface StorageService {

    boolean isImageFile(MultipartFile multipartFile);

    boolean isVideoFile(MultipartFile multipartFile);

    String storeFile(MultipartFile multipartFile,  Path resourcePath);

    boolean delete(String fileName, Path resourcePath);

}
