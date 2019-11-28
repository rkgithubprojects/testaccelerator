package com.cigniti.testaccelerator.exe;

import com.cigniti.testaccelerator.reports.GenerateReports;

public class Execute {

	public static void main(String[] args) throws Exception {
		try
		{
			String[] sheetNames={"Booking1"};
						
			/*ReadExcel readExcel=new ReadExcel();
			Set<List<String>> excelData=readExcel.readExcelData(sheetNames[0]);
			
			StoreExcelData storeExcelData=new StoreExcelData();
			Map<String, TestData> storeData=storeExcelData.getSheetData(sheetNames[0]);
			
			ReadStaticSteps rd=new ReadStaticSteps();
			Map<String, List<TestData>> staticData=rd.readStaticData();
			
			GenerateTestCase tc=new GenerateTestCase();
			tc.generateTestCase(excelData, storeData, staticData, sheetNames[0]);
			
			GenerateTestScript generateScript=new GenerateTestScript();
			generateScript.runTestScript(excelData, storeData,staticData,sheetNames[0]);*/
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			GenerateReports.getSummaryReports();
		}
	}
}
