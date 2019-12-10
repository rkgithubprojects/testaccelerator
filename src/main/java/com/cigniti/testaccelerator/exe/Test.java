package com.cigniti.testaccelerator.exe;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

	public static void main(String[] args) {
		boolean result = false;
		XSSFWorkbook tcWorkbook = null;
		FileOutputStream tcOutputStream = null;
		try {

			/*
			 * String sheetName = "Sheet1"; tcWorkbook = new XSSFWorkbook();
			 * File testCaseFile = new File("C:\\ABC\\A.xlsx"); tcOutputStream =
			 * new FileOutputStream(testCaseFile); Sheet tcOutsheet =
			 * tcWorkbook.createSheet(sheetName);
			 *
			 * Row row = tcOutsheet.createRow(0); Cell cell = row.createCell(0);
			 * cell.setCellValue("//span[text()='Flight']");
			 * 
			 * tcWorkbook.write(tcOutputStream); tcWorkbook.close();
			 * tcOutputStream.close();
			 * 
			 * Integer a=new Integer(3); int a1=a.intValue();
			 *
			 *
			 *
			 */

			/*
			 * WebDriver driver=new FirefoxDriver();
			 * driver.get("https://www.google.co.in/"); Thread.sleep(2000);
			 * Actions actions=new Actions(driver);
			 * actions.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).
			 * build().perform(); Thread.sleep(5000);
			 * System.out.println("Success");
			 */

			String s = "Hello,Hai!,How:are;you.";
			System.out.println(s);
			s = s.replace(",", "COMMA").replace("|", "LINE").replace(":", "XCOLON").replace(";", "SCOLON")
					.replace("'", "SQUOTE").replace("\"", "DQUOTE").replace("/", "SSLASH").replace("//", "DSLASH")
					.replace("(", "ROBRACE").replace(")", "RCBRACE").replace("]", "SCBRACE").replace("[", "SOBRACE");

			System.out.println(s);
			
			s=s.replace( "COMMA",",").replace( "LINE","|").replace("XCOLON",":").replace("SCOLON",";")
			.replace("SQUOTE","'").replace("DQUOTE","\"").replace("SSLASH","/").replace( "DSLASH","//")
			.replace( "ROBRACE","(").replace("RCBRACE",")").replace("SCBRACE","]").replace("SOBRACE","[");
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
