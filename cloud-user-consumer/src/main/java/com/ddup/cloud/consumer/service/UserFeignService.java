package com.ddup.cloud.consumer.service;

import com.ddup.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-user-service", path = "/user")
public interface UserFeignService {

    @GetMapping("get/{id}")
    User getUser(@PathVariable("id") String id);
}
