package com.project.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    public static Map<String, String> readRow(String filePath, String sheetName, int rowIndex) throws Exception {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row header = sheet.getRow(0);
            Row row = sheet.getRow(rowIndex);

            Map<String, String> rowData = new HashMap<>();
            for (int i = 0; i < header.getLastCellNum(); i++) {
                String key = header.getCell(i).getStringCellValue();
                String value = row.getCell(i).getStringCellValue();
                rowData.put(key, value);
            }
            return rowData;
        }
    }
}
