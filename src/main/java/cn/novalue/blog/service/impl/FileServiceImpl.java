package cn.novalue.blog.service.impl;

import cn.novalue.blog.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Wu Yangjie
 * @date 2020-05-16
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        String filename = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        File dest = new File(userDir, filename);
        if (!dest.getParentFile().exists())
            dest.getParentFile().mkdirs();
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/file/" + filename;
    }
}
