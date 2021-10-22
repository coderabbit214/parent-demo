package com.jsh.mapper;

import com.jsh.entity.InterfaceStatistics;

/**
 * @Author: Mr_J
 * @Date: 2021/10/22 3:14 下午
 */
public interface InterfaceStatisticsMapper {

    InterfaceStatistics findByUri(String uri);
}
