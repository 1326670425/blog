package cn.novalue.blog.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yangjie
 * @date 2020-08-25
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("admin")
public class AdminController {

}
