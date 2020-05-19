package cn.novalue.blog.controller;


import cn.novalue.blog.model.support.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Wu Yangjie
 * @date 2020-05-08
 */
@RestController
public class TestController {

    @CrossOrigin(allowCredentials = "true")
    @PostMapping("/hello")
    public Response hello(@RequestBody Map<String, String> map) {
        return Response.success("success", map.get("username"));
    }

}
