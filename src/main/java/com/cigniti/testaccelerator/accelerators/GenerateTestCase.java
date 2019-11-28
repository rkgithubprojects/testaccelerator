package com.cigniti.testaccelerator.accelerators;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cigniti.testaccelerator.utils.BaseClass;
import com.cigniti.testaccelerator.utils.TestData;

public class GenerateTestCase extends BaseClass{

	public static int tcCount=0;
	Map<Integer,String> generatedTestCases=new HashMap<>();
	/*
	 * Generate manual test case from given data combinations 
	 * @excelData : Data from excel sheet with all possible combinations.Each list object is a set of possible combination
	 * @storeData :  Data in the form of key value pair Key= "Feature:PossibleValue" Value="Locator data in the for of TestData Object"
	 */
	public void generateTestCase(Set<List<String>> excelData, Map<String, TestData> storeData,Map<String, List<TestData>> staticData, String sheetName,HttpSession session) {
		String testCase="";
		int testCaseCount=0;
		boolean status=false;
		try {
			/*if(propData.get("GenerateTestCases").equalsIgnoreCase("TRUE")){*/
			String s=(String) session.getAttribute("generateTC");
			if(s.equalsIgnoreCase("TRUE")){
			for (List<String> list : excelData) {

				List<String> tcSteps = new ArrayList<>();

				tcSteps = initialize(staticData);

				for (String key : list) {

					TestData data = storeData.get(key);

					String category = data.getCategory();
					String statement = data.getStatement();
					

					if (category.equalsIgnoreCase("ONEWAY")) {
						isOneWay = true;
					}

					tcSteps.add(statement.replace("$", category));
					/*tcSteps.addAll(getTestCaseSteps(data));*/
					tcSteps.addAll(addStaticSteps(staticData, data));
				}
				//writeTestCaseToFile(tcSteps, sheetName);
				testCase=createTestCase(tcSteps);
				testCaseCount++;
				generatedTestCases.put(testCaseCount, testCase);
			}
			
			status=writeTestCasesToExcel(generatedTestCases,session);
		}//if
		}//try
		catch (Exception e) {
			System.out.println("Exception in generate test cases");
			e.printStackTrace();
		}finally
		{
			if(!status)
			{
				System.out.println("Error in writing test cases to excel");
			}
		}
	}

	
	private boolean writeTestCasesToExcel(Map<Integer, String> generatedTestCases2,HttpSession session) {
		boolean status=false;
		XSSFWorkbook tcWorkbook=null;
		FileOutputStream tcOutputStream=null;
		try
		{
			String sheetName="Sheet1";
			tcWorkbook = new XSSFWorkbook();
			File testCaseFile = new File(session.getAttribute("modulePath")+"/GeneratedTestCases.xlsx");
			tcOutputStream = new FileOutputStream(testCaseFile);
		
			Sheet tcOutsheet = tcWorkbook.createSheet(sheetName);
			
			Row row=null;
			int rowCount=0;
			int listCount=0;
			String generatedTC="";
			
			for (int i = 0; i <= generatedTestCases2.size(); i++) {
				row=tcOutsheet.createRow(i);
				Cell cell0=row.createCell(0);
				Cell cell1=row.createCell(1);
				if(i==0)
				{
					cell0.setCellValue("TC Count");
					cell1.setCellValue("Generated Test Case");
				}
				else
				{
					generatedTC=generatedTestCases2.get(i);
					cell0.setCellValue(i);
					cell1.setCellValue(generatedTC);
				}				
			}
			tcWorkbook.write(tcOutputStream);
			status=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	/*
	 * Write test case steps into text file and store into a folder
	 * @tcSteps : Testcase steps to be written to file 
	 * @sheetName : Name of the test data sheet for which test cases are generated 
	 */
	
	



	private boolean writeTestCaseToFile(List<String> tcSteps,String sheetName,HttpSession session) {
		boolean status = false;
		try {
			tcCount++;
			String path=(String) session.getAttribute("modulePath");
			createFolder(path+"/" + sheetName);
			PrintWriter writer = new PrintWriter(path+"/" + sheetName + "/" + sheetName + "_" + tcCount + ".txt", "UTF-8");

			for (String step : tcSteps) {
				writer.println(step);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	private String createTestCase(List<String> tcSteps) {
		
		String testCase="";
		try {
			tcCount++;
			for (String step : tcSteps) {
				testCase=testCase+step+"\n";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testCase;
		
	}

	/*
	 * Execute initial steps such as navigate to url and verify home page
	 * @staticData verifications steps from excel sheet in the form of key and value pair
	 */
	
	private List<String> initialize(Map<String, List<TestData>> staticData) {
		List<String> steps=new ArrayList<>();
		try {
			List<TestData> initData = staticData.get("InitialSteps");
			for (TestData testData : initData) {
				steps.add(testData.getStatement());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return steps;
	}
	
	
	/* Add verification and static steps to dynamic steps
	 * @staticdata : set of verification steps in the form of key value pair
	 * @testData : TestData object 
	 */
	
	public List<String> addStaticSteps(Map<String, List<TestData>> staticData, TestData testData) {
		List<String> steps=new ArrayList<>();
		try {
			List<TestData> staticSteps = new ArrayList<>();
			staticSteps = getValidStaticSteps(staticData, testData);
			if (staticSteps != null && staticSteps.size() > 0) {
				for (TestData data : staticSteps) {
					steps.add(data.getStatement());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return steps;
	}
	
	/*
	 * Get Valid verification steps depending upon the given test data
	 * @staticData verification steps inthe form key value pair
	 * @testData : testData Object
	 */
	private List<TestData> getValidStaticSteps(Map<String, List<TestData>> staticData, TestData testData) {
		List<TestData> staticSteps = new ArrayList<>();
		try {

			staticSteps = staticData.get(testData.getFeatureType());
			if ((!isOneWay) && testData.getFeatureType().equalsIgnoreCase("CabinClassOut"))
			{
				staticSteps.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staticSteps;
	}
}
