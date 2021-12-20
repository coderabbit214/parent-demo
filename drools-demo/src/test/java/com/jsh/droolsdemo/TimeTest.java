package com.jsh.droolsdemo;

import com.jsh.droolsdemo.entity.Order;
import com.jsh.droolsdemo.util.MessageList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/12/17 2:18 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TimeTest {

    @Autowired
    private KieSession session;

    private final Integer num = 30000000;

//    5000000   11174
//    10000000  21780
//    15000000  35060
//    30000000  76778

    @Test
    public void test() {
        MessageList.init();

        long startTime = System.currentTimeMillis();
        log.info("开始" + new Date(startTime));
        for (int i = 0; i < num; i += 20000) {
            List<Order> list = new ArrayList<>();
            for (int j = 0; j < 20000; j++) {
                Order order = new Order();
                order.setOriginalPrice(Math.random() * 100);
                list.add(order);
            }
            rule(list);
            log.info(String.valueOf(i));
        }
        long endTime = System.currentTimeMillis();
        log.info("结束" + (new Date(endTime)));
        long intervalTime = endTime - startTime;
        log.info(String.valueOf(intervalTime));
        System.out.println(MessageList.testList.size());
    }

    private void rule(List<Order> list) {
        for (Order order : list) {
            FactHandle insert = session.insert(order);
            //激活规则引擎，如果规则匹配成功则执行规则
            session.fireAllRules();
            session.delete(insert);
        }

    }
}
