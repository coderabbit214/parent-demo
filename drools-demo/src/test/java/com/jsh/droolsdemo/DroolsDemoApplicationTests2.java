package com.jsh.droolsdemo;

import com.jsh.droolsdemo.entity.Animal;
import com.jsh.droolsdemo.entity.Cat;
import com.jsh.droolsdemo.entity.Order;
import com.jsh.droolsdemo.util.MessageList;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DroolsDemoApplicationTests2 {
    @Autowired
    private KieSession session;

    @Autowired
    private KieBase kieBase;

    private Map<String,String> map = new HashMap<>();

    @Test
    public void test1(){
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        Order order = new Order();
        order.setOriginalPrice(59D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        Order order1 = new Order();
        order1.setOriginalPrice(91D);

        MessageList.init();

        session.insert(order);
//        session.fireAllRules();
        session.insert(order1);
        //激活规则引擎，如果规则匹配成功则执行规则
        session.fireAllRules();
        //关闭会话
        session.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() +
                "，优惠后价格：" + order.getRealPrice());
        System.out.println("优惠前原始价格：" + order1.getOriginalPrice() +
                "，优惠后价格：" + order1.getRealPrice());
        for (String s : MessageList.testList) {
            System.out.println(s);
        }
    }

    @Test
    public void test2(){
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        Cat cat = new Cat();
        cat.setSex(0);
        Order order = new Order();
        order.setOriginalPrice(99D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        session.insert(order);
        session.insert(cat);

        //激活规则引擎，如果规则匹配成功则执行规则
        session.fireAllRules();
        //关闭会话
        session.dispose();
    }

    //测试from
    @Test
    public void test3(){
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        List<Cat> cats = new ArrayList<>();
        Cat cat = new Cat();
        cat.setSex(0);
        cat.setName("11");
        Cat cat1 = new Cat();
        cat1.setSex(0);
        cat1.setName("22");
        Cat cat2 = new Cat();
        cat2.setSex(0);
        cat2.setName("33");
        Cat cat3 = new Cat();
        cat3.setSex(0);
        cat3.setName("44");
        cats.add(cat);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        Animal animal = new Animal();
        animal.setCats(cats);
        session.insert(animal);

        //激活规则引擎，如果规则匹配成功则执行规则
        session.fireAllRules();
        //关闭会话
        session.dispose();
    }

//  测试uploda
    @Test
    public void test4(){
        Cat cat = new Cat();
        cat.setSex(0);
        session.insert(cat);

        //激活规则引擎，如果规则匹配成功则执行规则
        session.fireAllRules();
        //关闭会话
        session.dispose();

        for (String s : map.keySet()) {

        }
    }

}
