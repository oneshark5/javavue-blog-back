package com.oneshark.excel;/**
 * @Author bobo
 * @Date 2024/1/16 11:06
 * @version 1.0
 * @注释
 */

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelColumnToRow {
    public static void main(String[] args) {
        try {
            // 指定Excel文件路径
            String filePath = "path/to/your/excel/file.xlsx";

            // 指定列号，这里假设是第一列
            int columnIndex = 0;

            // 读取Excel文件
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 获取列总数
            int rowCount = sheet.getLastRowNum() + 1;

            // 创建数组来存储列数据
            String[] columnData = new String[rowCount];

            // 读取列数据
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(columnIndex);
                columnData[i] = cell.toString();
            }

            // 关闭文件流
            fileInputStream.close();

            // 将数组转换为一行字符串，以逗号分隔
            String rowData = String.join(", ", columnData);

            // 打印结果
            System.out.println(rowData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

