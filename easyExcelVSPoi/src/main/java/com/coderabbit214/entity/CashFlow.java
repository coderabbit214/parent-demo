package com.coderabbit214.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Mr_J
 * @Date: 2021/12/8 3:50 下午
 */
@Entity
@Table(name = "CASH_FLOWS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 收支 */
    @Column(name = "bOrP")
    @ExcelProperty(value = "收/支" ,index = 0)
    private String bOrP;

    /** 交易对方 */
    @Column(name = "COUNTERPARTY")
    @ExcelProperty(value = "交易对方" ,index = 1)
    private String counterparty;

    /** 对方账号 */
    @Column(name = "OTHER_ACCOUNTS")
    @ExcelProperty(value = "对方账号" ,index = 2)
    private String otherAccounts;

    /** 商品说明 */
    @Column(name = "PRODUCT_DESCRIPTION")
    @ExcelProperty(value = "商品说明" ,index = 3)
    private String productDescription;

    /** 收/付款方式 */
    @Column(name = "B_OR_P_METHOD")
    @ExcelProperty(value = "收/付款方式" ,index = 4)
    private String bOrpMethod;

    /** 金额 */
    @Column(name = "AMOUNT")
    @ExcelProperty(value = "金额" ,index = 5)
    private BigDecimal amount;

    /** 交易状态 */
    @Column(name = "TRADING_STATUS")
    @ExcelProperty(value = "交易状态" ,index = 6)
    private String tradingStatus;

    /** 交易分类 */
    @Column(name = "TRANSACTION_CLASSIFICATION")
    @ExcelProperty(value = "交易分类" ,index = 7)
    private String transactionClassification;

    /** 交易订单号 */
    @Column(name = "TRANSACTION_ORDER_NUMBER")
    @ExcelProperty(value = "交易订单号" ,index = 8)
    private String transactionOrderNumber;

    /** 商家订单号 */
    @Column(name = "MERCHANT_ORDER_NUMBER")
    @ExcelProperty(value = "商家订单号" ,index = 9)
    private String merchantOrderNumber;;

    /** 交易时间 */
    @Column(name = "TRANSACTION_HOUR")
    @ExcelProperty(value = "交易时间" ,index = 10)
    private Date transactionHour;

}

