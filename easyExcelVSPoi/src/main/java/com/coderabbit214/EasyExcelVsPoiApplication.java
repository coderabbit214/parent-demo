package com.coderabbit214;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(value={"com.coderabbit214.*","com.coderabbit214.dao"})
@EnableAsync
@EnableScheduling
public class EasyExcelVsPoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyExcelVsPoiApplication.class, args);
    }

}
