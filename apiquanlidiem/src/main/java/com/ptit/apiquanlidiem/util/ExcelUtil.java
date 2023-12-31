package com.ptit.apiquanlidiem.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ExcelUtil {

    public static boolean checkExcelFormat(MultipartFile file){
        return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                ?true
                :false;

    }

    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        if (file.getOriginalFilename().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (file.getOriginalFilename().endsWith("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    public static Object getCellValue( Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
