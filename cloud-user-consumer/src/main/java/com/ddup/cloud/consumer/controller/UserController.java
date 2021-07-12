package com.ddup.cloud.consumer.controller;

import com.ddup.cloud.consumer.service.UserFeignService;
import com.ddup.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class UserController {
    private static final String URL = "http://cloud-user-service/user/";
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("get/{id}")
    public User getUser(@PathVariable("id") String id) {
        String url = URL + "get/" + id;
        ResponseEntity<User> entity = restTemplate.getForEntity(url, User.class);
        return entity.getBody();
    }

    @GetMapping("get/s/{id}")
    public User getStaticUser(@PathVariable("id") String id) {
        System.out.println(id);

        return new User(33, "还是那山", "男", 22, "茶花大道");
    }

    @GetMapping("/feign/{id}")
    public User getUserByFeign(@PathVariable("id") String id) {
        log.debug("接收到请求");
        User user = userFeignService.getUserByFeign(id);
        return user;
    }
}
