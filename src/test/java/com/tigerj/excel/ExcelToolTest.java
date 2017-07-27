package com.tigerj.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.alibaba.fastjson.JSON;

public class ExcelToolTest {
		
	public List<List<String>> read(String filePath){
	    try {
			InputStream inp = new FileInputStream(filePath);
		    Workbook wb = WorkbookFactory.create(inp);
		    System.out.println("NumberOfSheets :"+wb.getNumberOfSheets());
		    DataFormatter formatter = new DataFormatter();
		    Sheet sheet = wb.getSheetAt(0);
		    int lastRowNumber = sheet.getLastRowNum();
		    System.out.println("lastRowNumber:"+lastRowNumber);
		    
		    
		    List<List<String>> list = new ArrayList<>();
		    for (int i=0;i<=lastRowNumber;i++) {
		    	List<String> rowList = new ArrayList<>();
		    	for (Cell cell : sheet.getRow(i)) {
		    		//CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
		            //System.out.print(cellRef.formatAsString());
		            //System.out.print(" - ");
		            String text = formatter.formatCellValue(cell);
		            //System.out.println(text);
		            rowList.add(text);
		    	}
		    	list.add(rowList);
		    }

		    return list;			
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	 
	public static void main(String[] args) {
		String filePath = "/Users/shihujiang/Documents/003private/shuping.zhao/data/6.xls";
		List<List<String>> list =  new ExcelToolTest().read(filePath);
		System.out.println(JSON.toJSONString(list));
		
	}

}
