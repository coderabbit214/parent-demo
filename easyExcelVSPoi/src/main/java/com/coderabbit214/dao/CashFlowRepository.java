package com.coderabbit214.dao;

import com.coderabbit214.entity.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mr_J
 * @Date: 2021/12/8 4:26 下午
 */
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

    @Query(value = "SELECT * FROM cash_flows limit :num",nativeQuery = true)
    public List<CashFlow> limit(Integer num);
}
