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
        // String encodePassword = BCryptUtils.encode("Abc2120669");
        // System.out.println(encodePassword);
        System.out.println(BCryptUtils.matches("Abc2120669", "$2a$12$0z6pMhIQLH1.3ICUTTo5hOM1L0wdgoigsXUhEqQyTnIecn5l.FPJi"));
        System.out.println(BCryptUtils.matches("123456", "$2a$12$3X.OiGmt1abS48tBbapc2eV38Da/eZp4LsFGJdnjoqtklmLnWzBqu"));
    }
}
