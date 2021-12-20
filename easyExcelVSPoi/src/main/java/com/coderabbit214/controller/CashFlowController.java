package com.coderabbit214.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.coderabbit214.dao.CashFlowRepository;
import com.coderabbit214.easyExcelListener.CashExcelListener;
import com.coderabbit214.entity.CashFlow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/12/8 4:45 下午
 */
@RestController("/cashFlow")
@Api(tags = "账单")
@Slf4j
public class CashFlowController {

    @Autowired
    private CashFlowRepository cashFlowRepository;

    //    52748
    @ApiOperation("导出Easy")
    @GetMapping("/exportCashFlowsToByEasy")
    public void exportCashFlowsToByEasy(HttpServletResponse response) throws UnsupportedEncodingException {
        List<CashFlow> all = cashFlowRepository.limit(60000); 

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 如果不用模板的方式导出的话，是doWrite
        try {
            Date date = new Date();
            log.info("开始");
            EasyExcel.write(response.getOutputStream(), CashFlow.class).sheet("测试").doWrite(all);
            log.info("结束,时间：" + (System.currentTimeMillis() - date.getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("导出")
    @GetMapping("/exportCashFlowsToByPOI")
    public void exportCashFlowsToByPOI(HttpServletResponse response) throws IOException {
        List<CashFlow> all = cashFlowRepository.limit(60000); //4997
        Date date = new Date();
        log.info("开始");
        HSSFWorkbook wb = new HSSFWorkbook();//创建HSSFWorkbook对象
        HSSFSheet sheet = wb.createSheet("测试");//建立sheet对象
        //在sheet里创建第二行
        HSSFRow row1 = sheet.createRow(0);
        //创建单元格并设置单元格内容
        row1.createCell(0).setCellValue("id");
        row1.createCell(1).setCellValue("收/支");
        row1.createCell(2).setCellValue("交易对方");
        row1.createCell(3).setCellValue("对方账号");
        row1.createCell(4).setCellValue("商品说明");
        row1.createCell(5).setCellValue("收/付款方式");
        row1.createCell(6).setCellValue("金额");
        row1.createCell(7).setCellValue("交易状态");
        row1.createCell(8).setCellValue("交易分类");
        row1.createCell(9).setCellValue("交易订单号");
        row1.createCell(10).setCellValue("商家订单号");
        row1.createCell(11).setCellValue("交易时间");
        //在sheet里创建第三行
        int m = 0;
        for (int i = 0; i < all.size(); i++) {
            if ((i + 1) % 65535 == 0) {
                sheet = wb.createSheet("测试"+i);//建立sheet对象
                m=0;
            }
            HSSFRow row = sheet.createRow(m + 1);
            row.createCell(0).setCellValue(all.get(i).getId());
            row.createCell(1).setCellValue(all.get(i).getBOrP());
            row.createCell(2).setCellValue(all.get(i).getCounterparty());
            row.createCell(3).setCellValue(all.get(i).getOtherAccounts());
            row.createCell(4).setCellValue(all.get(i).getProductDescription());
            row.createCell(5).setCellValue(all.get(i).getBOrpMethod());
//            row.createCell(6).setCellValue((RichTextString) all.get(i).getAmount());
            row.createCell(7).setCellValue(all.get(i).getTradingStatus());
            row.createCell(8).setCellValue(all.get(i).getTransactionClassification());
            row.createCell(9).setCellValue(all.get(i).getTransactionOrderNumber());
            row.createCell(10).setCellValue(all.get(i).getMerchantOrderNumber());
            row.createCell(11).setCellValue(all.get(i).getTransactionHour());
            m++;
        }
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=Student.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        log.info("结束,时间：" + (System.currentTimeMillis() - date.getTime()));
    }

    @ApiOperation("导入Easy")
    @PostMapping("/exportCashFlowsFromByEasy")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "file")
    )
    public void exportCashFlowsFromByEasy(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), CashFlow.class, new CashExcelListener(cashFlowRepository)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
