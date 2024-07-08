package Oasys.Oasysapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadExcelFile {

	private XSSFWorkbook xssfworkbook;
	private XSSFSheet xssfSheet;
	private XSSFRow xssfRow;
	private XSSFCell xssfCell, xssfCell1;

	private FileInputStream fis;
	private FileOutputStream fos;

	/*
	 * @Test(dataProvider="LoginData") public void Test(String name,String password)
	 * { System.out.println(name.trim()); System.out.println(password.trim());
	 * 
	 * 
	 * }
	 */

	@DataProvider(name = "testdata")
	public Object[][] getFile() throws IOException {
		Object[][] object;
		int rowsize;
		int cellSize;
		int cellValue;

//		fis=new FileInputStream(System.getProperty("user.dir")+"//DataExcel//Data.xlsx");

//		File file=new File(System.getProperty("user.dir") + "//DataExcel//Data.xlsx");

//		try {
		fis = new FileInputStream(System.getProperty("user.dir") + "//Externaldata//nameandpass.xlsx");
		System.out.println("this is executed");
//	s

//		try {
		xssfworkbook = new XSSFWorkbook(fis);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		else {
//			System.out.println("File Doesnot exist...");
//		
//		}

		xssfSheet = xssfworkbook.getSheet("Sheet1");
		System.out.println("read the execel sheet");
		rowsize = xssfSheet.getLastRowNum();
		cellValue = xssfSheet.getRow(0).getLastCellNum();
		cellSize = Integer.valueOf(cellValue);

		System.out.println("RowSize=" + rowsize);
		System.out.println("CellSize=" + cellSize);

		// initialize the Object[][].
		object = new String[rowsize + 1][2];

//
		// store all the excel value to two dimensional array which is object[][].
		for (int i = 0; i <= rowsize; i++) {
			for (int j = 0; j < cellSize; j++) {
//				if (j==) {
//					System.out.println(xssfSheet.getRow(i).getCell(j).getNumericCellValue());
//					double value = xssfSheet.getRow(i).getCell(j).getNumericCellValue();
////					System.out.println("This is for only 7 cell"+value);
//					object[i][j] = String.valueOf(xssfSheet.getRow(i).getCell(j).getNumericCellValue());
////					System.out.println("This is for only 7 cell");
//				} 

				System.out.println(xssfSheet.getRow(i).getCell(j).getStringCellValue());
				object[i][j] = xssfSheet.getRow(i).getCell(j).getStringCellValue();
//					System.out.println("This is for all");

			}

		}
//		
		System.out.println("row number" + object[1].length);
		// return this
		for (int i = 0; i <= rowsize; i++) {
			for (int j = 0; j < cellSize; j++) {
				System.out.println("object print" + object[i][j]);
			}
		}

		return object;

	}

	public void create_Cell1() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + "//Externaldata//nameandpass.xlsx");
		xssfworkbook = new XSSFWorkbook(fis);
		xssfSheet = xssfworkbook.getSheet("Sheet1");
		short cellnum = xssfSheet.getRow(0).getLastCellNum();
		Short cell_value = cellnum;
		System.out.println("cell value" + cellnum);
		while (cellnum != 0) {
			System.out.println("cell value=" + xssfSheet.getRow(0).getCell(cellnum - 1).getStringCellValue());
			cellnum--;
		}

		// create cell
		if (xssfSheet.getRow(0).getCell(cell_value - 1).getStringCellValue().equals("Password")) {
			xssfSheet.getRow(0).createCell(cell_value).setCellValue("phone no");
			fos = new FileOutputStream(System.getProperty("user.dir") + "//Externaldata//nameandpass.xlsx");
			xssfworkbook.write(fos);
			fos.close();
		}

		System.out.println("Lastcell number after creating one cell.." + xssfSheet.getRow(0).getLastCellNum());
		fis.close();
		System.out.println("Successfully created....");

	}

//	public void inserAndEditCell(String result,int index) throws IOException {
//		// change-1
//		fis = new FileInputStream(System.getProperty("user.dir") + "//Externaldata//BimsDataAuto1.xlsx");
//		xssfworkbook = new XSSFWorkbook(fis);
//		xssfSheet = xssfworkbook.getSheet("Sheet1");
//		System.out.println("Row num=" + xssfSheet.getLastRowNum());
//		System.out.println("cell value=" + xssfSheet.getRow(index).getLastCellNum());
////		int lastcellvalue=xssfSheet.getRow(0).getLastCellNum()-1;
////		System.out.println("last cell value="+lastcellvalue);
//		
//		
//		int count = 0;
//		Iterator<Cell> eachcell = xssfSheet.getRow(0).cellIterator();
//	
//		
////		while(eachcell.hasNext()) {			
////			System.out.println("cell number=" + (count++) + "cell type=" + eachcell.next().getCellType()+"value="+eachcell.next().getStringCellValue());
////		}
//
//		// create cell if the cell not exit
//		System.out.println("last cell value=" + xssfSheet.getRow(index).getCell(xssfSheet.getRow(index).getLastCellNum() - 1).getStringCellValue());
//		System.out.println(xssfSheet.getRow(0).getCell(xssfSheet.getRow(0).getLastCellNum()- 1).getStringCellValue());
//
//		// create the cell
//		if (xssfSheet.getRow(0).getCell(xssfSheet.getRow(0).getLastCellNum() - 1).getStringCellValue()
//				.equals("Aadhar_VoterNo")) {
//			xssfSheet.getRow(0).createCell(xssfSheet.getRow(0).getLastCellNum()).setCellValue("Status");
//			// change-2
//			FileOutputStream fos = new FileOutputStream(
//					System.getProperty("user.dir") + "//Externaldata//BimsDataAuto1.xlsx");
//			xssfworkbook.write(fos);
//			fos.close();
////		System.out.println("Successfull write in excel....");
////		System.out.println("get cell value="+xssfSheet.getRow(0).getCell(xssfSheet.getRow(0).getLastCellNum()).getCellType());
//		}
//		
//		int lastcellvalue=xssfSheet.getRow(index).getLastCellNum();
//		System.out.println("last cell value="+lastcellvalue);
//		
//		fis.close();
//		fis = new FileInputStream(System.getProperty("user.dir") + "//Externaldata//BimsDataAuto1.xlsx");
//		xssfworkbook = new XSSFWorkbook(fis);
//		xssfSheet = xssfworkbook.getSheet("Sheet1");
//		System.out.println("Row num=" + xssfSheet.getLastRowNum());
//		System.out.println("cell size=" + xssfSheet.getRow(1).getLastCellNum());
//		System.out.println("cell value="+xssfSheet.getRow(index).getCell(xssfSheet.getRow(index).getLastCellNum()-1).getStringCellValue());
//		
//		if(xssfSheet.getRow(1).getCell(xssfSheet.getRow(1).getLastCellNum()-1).getStringCellValue()=="") {
//			System.out.println("printing hhhhh...");
//		}
//		
//		
//		
//		System.out.println(
//				"get cell Type=" + xssfSheet.getRow(index).getCell(xssfSheet.getRow(index).getLastCellNum() - 1).getCellType());
//		System.out.println("get cell value="
//				+ xssfSheet.getRow(0).getCell(xssfSheet.getRow(0).getLastCellNum() - 1).getStringCellValue());
//
//		// set value the cell
//		if (xssfSheet.getRow(0).getCell(xssfSheet.getRow(0).getLastCellNum() - 1).getStringCellValue()
//				.equals("Status")) {
//			// remove cell
////		xssfCell1=xssfSheet.getRow(0).getCell(xssfSheet.getRow(0).getLastCellNum()-1);
////		xssfSheet.getRow(0).removeCell(xssfCell1);
////		
//			// here we set the cell value
//			xssfSheet.getRow(0).getCell(xssfSheet.getRow(0).getLastCellNum() - 1).setCellValue("Result output");
//
//			// change-3
//			FileOutputStream fileOut = new FileOutputStream(
//					System.getProperty("user.dir") + "//Externaldata//BimsDataAuto1.xlsx");
//			xssfworkbook.write(fileOut);
//			fileOut.close();
//	
//		}
//		//fis.close();
//		
//		
//		//
////		fis = new FileInputStream(System.getProperty("user.dir") + "//Externaldata//BimsDataAuto1.xlsx");
////		xssfworkbook = new XSSFWorkbook(fis);
////		xssfSheet = xssfworkbook.getSheet("Sheet1");
////		System.out.println("Row num=" + xssfSheet.getLastRowNum());
////		System.out.println("cell size=" + xssfSheet.getRow(1).getLastCellNum());
////		System.out.println("cell value="+xssfSheet.getRow(index).getCell(xssfSheet.getRow(index).getLastCellNum()-1).getStringCellValue());
//		System.out.println("cell type="+xssfSheet.getRow(index).getCell(lastcellvalue-1).getCellType());
//
//		//create cell for status cell
//		if (xssfSheet.getRow(index).getCell(lastcellvalue-1).getCellType()
//				.equals(CellType.BLANK)) {
//			xssfSheet.getRow(index).createCell(lastcellvalue-1).setCellValue(result);
//			FileOutputStream fos = new FileOutputStream(
//					System.getProperty("user.dir") + "//Externaldata//BimsDataAuto1.xlsx");
//			xssfworkbook.write(fos);
//			fos.close();
//		}
//		
//		//xssfSheet.getRow(index).getLastCellNum() - 1
//		
//		
//		else if(!xssfSheet.getRow(index).getCell(lastcellvalue-1).getCellType()
//				.equals(CellType.BLANK)) {
//
//			System.out.println("print index="+index);
//			System.out.println("cell num="+xssfSheet.getRow(index).getLastCellNum());
//			xssfSheet.getRow(index).getCell(lastcellvalue-1).setCellValue("Orange"+result+" "+"apple");
//			FileOutputStream fileOut = new FileOutputStream(
//					System.getProperty("user.dir") + "//Externaldata//BimsDataAuto1.xlsx");
//			xssfworkbook.write(fileOut);
//			fileOut.close();
//			System.out.println("updated......");
//			
//		}
//
//	}

}
