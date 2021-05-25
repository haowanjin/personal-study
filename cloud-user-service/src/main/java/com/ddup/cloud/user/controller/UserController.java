package com.ddup.cloud.user.controller;

import com.ddup.user.api.UserService;
import com.ddup.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("get/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("list")
    public List<User> listUser() {
        return userService.listUser();
    }

    @PostMapping("save")
    @ResponseBody
    public String saveUser(User user) {

        userService.saveUser(user);

        return "Success";
    }


}
