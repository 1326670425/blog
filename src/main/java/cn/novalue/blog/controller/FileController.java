package cn.novalue.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Wu Yangjie
 * @date 2020-05-12
 */
@RestController
public class FileController {
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String filename = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();

//      String userDir = new ApplicationHome(getClass()).getSource().getParentFile().toString();
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
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        return "/file/" +filename;
    }
}
