package com.gerry.pang.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.omg.PortableServer.POAPackage.WrongAdapter;

public class ExcelUtils {
	public int getNumbersOfSheet(String fileName){
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(fileName)));
			return workbook.getNumberOfSheets();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
