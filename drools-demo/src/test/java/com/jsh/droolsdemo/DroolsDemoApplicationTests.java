package com.jsh.droolsdemo;

import com.jsh.droolsdemo.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class DroolsDemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    public void test1(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession();

        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        Order order = new Order();
        order.setOriginalPrice(210D);

        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);

        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();

        System.out.println("优惠前原始价格：" + order.getOriginalPrice() +
                "，优惠后价格：" + order.getRealPrice());
    }

    //使用字符串
    @Test
    public void test2(){
        String myRule = "//图书优惠规则\n" +
                "package book.discount\n" +
                "import com.jsh.droolsdemo.entity.Order\n" +
                "\n" +
                "//规则一：所购图书总价在100元以下的没有优惠\n" +
                "rule \"book_discount_1\"\n" +
                "    when\n" +
                "        $order:Order(originalPrice < 100)\n" +
                "    then\n" +
                "        $order.setRealPrice($order.getOriginalPrice());\n" +
                "        System.out.println(\"成功匹配到规则一：所购图书总价在100元以下的没有优惠\");\n" +
                "end\n" +
                "\n" +
                "//规则二：所购图书总价在100到200元的优惠20元\n" +
                "rule \"book_discount_2\"\n" +
                "    when\n" +
                "        $order:Order(originalPrice < 200 && originalPrice >= 100)\n" +
                "    then\n" +
                "        $order.setRealPrice($order.getOriginalPrice() - 20);\n" +
                "        System.out.println(\"成功匹配到规则二：所购图书总价在100到200元的优惠20元\");\n" +
                "end\n" +
                "\n" +
                "//规则三：所购图书总价在200到300元的优惠50元\n" +
                "rule \"book_discount_3\"\n" +
                "    when\n" +
                "        $order:Order(originalPrice <= 300 && originalPrice >= 200)\n" +
                "    then\n" +
                "        $order.setRealPrice($order.getOriginalPrice() - 50);\n" +
                "        System.out.println(\"成功匹配到规则三：所购图书总价在200到300元的优惠50元\");\n" +
                "end\n" +
                "\n" +
                "//规则四：所购图书总价在300元以上的优惠100元\n" +
                "rule \"book_discount_4\"\n" +
                "    when\n" +
                "        $order:Order(originalPrice >= 300)\n" +
                "    then\n" +
                "        $order.setRealPrice($order.getOriginalPrice() - 100);\n" +
                "        System.out.println(\"成功匹配到规则四：所购图书总价在300元以上的优惠100元\");\n" +
                "end";
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        Order order = new Order();
        order.setOriginalPrice(210D);

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(myRule, ResourceType.DRL);
        KieSession kieSession = kieHelper.build().newKieSession();

        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();

        System.out.println("优惠前原始价格：" + order.getOriginalPrice() +
                "，优惠后价格：" + order.getRealPrice());
    }
}
