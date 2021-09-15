package com.ddup.spring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/9/15 11:47
 */
@RestController
@RequestMapping("index")
public class LoginController {

    @PreAuthorize("")
    @RequestMapping("/")
    public String index() {
        return "Success";
    }
}
