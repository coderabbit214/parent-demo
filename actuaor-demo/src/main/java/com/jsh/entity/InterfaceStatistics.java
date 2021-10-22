package com.jsh.entity;

import lombok.Data;

/**
 * @Author: Mr_J
 * @Date: 2021/10/22 1:50 下午
 */
@Data
public class InterfaceStatistics {
    /** id */
    private Long id;

    /** 请求路径 */
    private String uri;

    /** 请求ip */
    private String ip;

    /** 请求时间 单位：毫秒 */
    private Long date;

    /** 请求次数 */
    private String num;
}
