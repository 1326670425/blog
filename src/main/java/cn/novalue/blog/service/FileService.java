package cn.novalue.blog.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileService {
    String upload(MultipartFile file, HttpServletRequest request);
}
