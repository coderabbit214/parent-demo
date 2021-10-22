package com.jsh.service.impl;

import com.jsh.entity.InterfaceStatistics;
import com.jsh.mapper.InterfaceStatisticsMapper;
import com.jsh.service.InterfaceStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Mr_J
 * @Date: 2021/10/22 3:14 下午
 */
@Service
public class InterfaceStatisticsServiceImpl implements InterfaceStatisticsService {

    @Resource
    private InterfaceStatisticsMapper interfaceStatisticsMapper;

    @Override
    public void add(InterfaceStatistics interfaceStatistics) {
        InterfaceStatistics in = interfaceStatisticsMapper.findByUri(interfaceStatistics.getUri());
    }
}
