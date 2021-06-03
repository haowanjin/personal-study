package com.ddup.cloud.consumer.service;

import com.ddup.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFeignFallBackService implements UserFeignService {
    @Override
    public User getUser(String id) {
        log.debug("调用 UserService getUser 方法失败，进入降级处理方法 getUser");

        return new User(-1, "降级处理", "中性", 20, "失败，降级处理");
    }
}
