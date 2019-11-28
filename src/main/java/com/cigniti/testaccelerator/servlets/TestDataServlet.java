package com.cigniti.testaccelerator.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cigniti.testaccelerator.supportclasses.TestDataSupport;
import com.cigniti.testaccelerator.utils.HtmlData;

/**
 * Servlet implementation class TestDataServlet
 */
@WebServlet("/testDataServlet")
public class TestDataServlet extends HttpServlet {

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result=false;
		try {
			
			Map<Integer, HtmlData> testData=new HashMap<>();
			int rowCount=0;
			
			HttpSession session=request.getSession();
			String[] features=request.getParameterValues("feature");
			String[] possibleValues=request.getParameterValues("possibleValue");
			String[] statements=request.getParameterValues("statement");
			String[] locatorTypes=request.getParameterValues("locatorType");
			String[] locatorValues=request.getParameterValues("locatorValue");
			String[] operations=request.getParameterValues("operation");
			String[] textDatas=request.getParameterValues("textData");
			
			String locatorValue="";
			for (int i = 0; i < features.length; i++)
			{
				
				
				rowCount++;
				HtmlData rowData=new HtmlData();
				rowData.setFeature(features[i]);
				rowData.setPossibleValue(possibleValues[i]);
				rowData.setStatement(statements[i]);
				rowData.setLocatorType(locatorTypes[i]);
				
				
				locatorValue=locatorValues[i];
				
				locatorValue=locatorValue.replace( "COMMA",",").replace( "LINE","|").replace("XCOLON",":").replace("SCOLON",";")
						.replace("SQUOTE","'").replace("DQUOTE","\"").replace("SSLASH","/").replace( "DSLASH","//")
						.replace( "ROBRACE","(").replace("RCBRACE",")").replace("SCBRACE","]").replace("SOBRACE","[").replace("EQUALTO","=");
				
				
				rowData.setLocatorValue(locatorValue);
				rowData.setOperation(operations[i]);
				rowData.setTextData(textDatas[i]);
				testData.put(rowCount, rowData);
			}
			
			Map<String,List<HtmlData>> formatData = new HashMap<>(); 
			formatData=formatData(testData);
			if(formatData!=null)
			{
				
				TestDataSupport ts=new TestDataSupport();
				result=ts.writeToExcelDocument(formatData,session);
			}
			
			if(result)
			{
				response.sendRedirect("testdata.jsp");
			}
			else
			{
				response.sendRedirect("Error.jsp");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	

	private void displayData(Map<String, List<HtmlData>> formatData) {
		
		for (String key : formatData.keySet()) {
			List<HtmlData> li=formatData.get(key);
			
			for (HtmlData hd : li) {
				
				System.out.println(hd.getFeature()+","+hd.getLocatorType()+","+hd.getLocatorValue()+","+hd.getOperation()+","+hd.getPossibleValue()+","+hd.getTextData()+","+hd.getStatement());
			}
			
		}
	}

	private Map<String, List<HtmlData>> formatData(Map<Integer, HtmlData> testData) {
		Map<String, List<HtmlData>> mdata=new LinkedHashMap<>();
		try
		{
			int recordsCount=testData.size();
			
			for (int i = 1; i <= recordsCount; i++) {
				
				String key=testData.get(i).getFeature();
				HtmlData value=testData.get(i);
				
				if(!mdata.containsKey(key))
				{
					List<HtmlData> list1=new ArrayList<>();
					
					list1.add(value);
					mdata.put(key,list1);
				}
				else
				{	
					List<HtmlData> list2=new ArrayList<>();
					list2=mdata.get(key);
					
					list2.add(value);
					mdata.put(key, list2);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mdata;
	}
}
