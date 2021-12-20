package com.coderabbit214.easyExcelListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.coderabbit214.dao.CashFlowRepository;
import com.coderabbit214.entity.CashFlow;

/**
 * @Author: Mr_J
 * @Date: 2021/12/8 5:12 下午
 */
public class CashExcelListener extends AnalysisEventListener<CashFlow> {


    private CashFlowRepository cashFlowRepository;

    public CashExcelListener() {
    }

    public CashExcelListener(CashFlowRepository cashFlowRepository) {
        this.cashFlowRepository = cashFlowRepository;
    }

    @Override
    public void invoke(CashFlow cashFlow, AnalysisContext analysisContext) {
        cashFlowRepository.save(cashFlow);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
