package com.coderabbit214.job;

import com.alibaba.excel.EasyExcel;
import com.coderabbit214.dao.CashFlowRepository;
import com.coderabbit214.easyExcelListener.CashExcelListener;
import com.coderabbit214.entity.CashFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * @Author: Mr_J
 * @Date: 2021/12/8 6:22 下午
 */
@Component
public class Job {
    @Autowired
    private CashFlowRepository cashFlowRepository;
//    @Scheduled(cron = "0/2 * * * * ?")
    public void hello(){
        File file = new File("/Users/Mr_J/Desktop/1.xlsx");
        EasyExcel.read(file, CashFlow.class,new CashExcelListener(cashFlowRepository)).sheet().doRead();
    }
}
