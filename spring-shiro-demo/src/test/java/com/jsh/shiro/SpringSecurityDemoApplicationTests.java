package com.jsh.shiro;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

    List<String> strings = new ArrayList<>();
    @Test
    public void contextLoads() {
        for (String string : strings) {
            System.out.println(string);
        }
//        (withCompanyList)
    }

}
