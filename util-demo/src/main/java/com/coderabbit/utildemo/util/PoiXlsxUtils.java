package com.coderabbit.utildemo.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PoiXlsxUtils {

    /**
     * 写入 Excel
     *
     * @param sheetName    工作簿的名称
     * @param headerMapper 标题的 Mapper key 为 Excel 的表头，value 为 data 中每个 Map 的 key
     * @param data         数据 List of Map
     * @return XSSFWorkbook
     */
    public static XSSFWorkbook generateSheet(String sheetName, LinkedHashMap<String, String> headerMapper, List<? extends Map<String, Object>> data) {
        // 检查工作簿的名称，排除非法字符
        XSSFWorkbook wb = new XSSFWorkbook();
        return generateSheet(wb, sheetName, headerMapper, data);
    }

    /**
     * 写入 Excel
     *
     * @param wb           excel名称
     * @param sheetName    工作簿的名称
     * @param headerMapper 标题的 Mapper key 为 Excel 的表头，value 为 data 中每个 Map 的 key
     * @param data         数据 List of Map
     * @return XSSFWorkbook
     */
    public static XSSFWorkbook generateSheet(XSSFWorkbook wb, String sheetName, LinkedHashMap<String, String> headerMapper, List<? extends Map<String, Object>> data) {
        // 检查工作簿的名称，排除非法字符
        sheetName = WorkbookUtil.createSafeSheetName(sheetName);
        XSSFSheet sheet = wb.createSheet(sheetName);
        // 首先写入首行，标题
        Row header = sheet.createRow(0);
        int i = 0;
        for (Map.Entry<String, String> entry : headerMapper.entrySet()) {
            String k = entry.getKey();
            header.createCell(i++).setCellValue(k);
        }
        // 从 index 1 开始继续写入余下的数据
        for (i = 0; i < data.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            int j = 0;
            for (Map.Entry<String, String> entry : headerMapper.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if (!header.getCell(j).getStringCellValue().equals(k)) {
                    continue;
                }
                Object currentCellDatum = data.get(i).getOrDefault(v, "");
                String currentCellString = null == currentCellDatum ? "" : currentCellDatum.toString();
                dataRow.createCell(j).setCellValue(currentCellString);
                j++;
            }
        }
        return wb;
    }

}
