package demo.test;

import com.ddup.spring.service.impl.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DITest {
    @Autowired
    private GoodsService goodsService;


    @Test
    public void test() {
        goodsService.getUser();
    }
}
