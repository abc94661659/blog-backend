package org.example;

import org.example.mapper.UserMapper;
import org.example.utils.BCryptUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogBackendApplicationTests {
    @Autowired
    private UserMapper userMapper;



    @Test
    public void test() {
        
    }
}
