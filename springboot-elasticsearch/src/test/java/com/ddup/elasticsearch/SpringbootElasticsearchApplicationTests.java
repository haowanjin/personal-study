package com.ddup.elasticsearch;

import com.ddup.elasticsearch.service.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringbootElasticsearchApplicationTests {

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() throws IOException {
        userController.createIndex("user");
    }

}
