package com.cigniti.testaccelerator.reports;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import com.cigniti.testaccelerator.utils.BaseClass;

public class GenerateReports extends BaseClass{

	public static void getSummaryReports() throws Exception
	{
		try {
			Writer writer = null;
			totalPax=adultsCount+seniorsCount;
			int passCount=0;
			int failCount=0;
			//int totalCount=passCount+failCount;
			File file = new File("/Users/ctl-user/Documents/AirlineRecent/Output/Reports/SummaryReports.html");
			

			writer = new FileWriter(file, false);
			writer.write("<html><head><style>table, th, td {border-collapse: collapse; border: 1px solid black;}</style><body bgcolor='#ffffff'>");
			writer.write("<center>");
			writer.write("<br><br><br>");

			/************************* Table 1 **************************/

			writer.write("<table width='90%'  border=1><tr bgcolor='violet'><th align='center' width='30%'><img src='http://www.cigniti.com/sites/all/themes/venture_theme/logo.png' alt='CignitiLogo'/></th><th align='center'>SOUTH WEST AUTOMATION REPORTS</th></tr></table>");
			writer.write("<br>");

			/************************* Table 2 **************************/

			writer.write("<table width='90%' border='1' bgcolor='ffcccc'>");

			writer.write("<tr width='100%'>");
			writer.write("<td width='30%' align='center'><b><font size='2'>URL</font></b></td>");
			writer.write("<td width='70%' align='center'><b><i><font size='2' color='red'>" + propData.get("URL")
					+ "</font><i></b></td>");
			writer.write("</tr>");

			writer.write("<tr width='100%'>");
			writer.write("<td width='30%' align='center'><b><font size='2'>BROWSER</font></b></td>");
			writer.write("<td width='70%' align='center'><b><i><font size='2' color='red'>"+ "FireFox" + "</font><i></b></td>");
			writer.write("</tr>");

			writer.write("<tr width='100%'>");
			writer.write("<td width='30%' align='center'><b><font size='2'>EXECUTION START-UP TIME</font></b></td>");
			writer.write("<td width='70%' align='center'><b><i><font size='2' color='red'>" + executionStartTime+ "</font><i></b></td>");
			writer.write("</tr>");
			
			writer.write("<tr width='100%'>");
			writer.write("<td width='30%' align='center'><b><font size='2'>EXECUTION END TIME</font></b></td>");
			writer.write("<td width='70%' align='center'><b><i><font size='2' color='red'>" + executionEndTime
					+ "</font><i></b></td>");
			writer.write("</tr>");
			
			writer.write("<tr width='100%'>");
			writer.write("<td width='30%' align='center'><b><font size='2'>TOTAL PASSENGERS</font></b></td>");
			writer.write("<td width='70%' align='center'><b><i><font size='2' color='red'>"
					+ totalPax + "</font><i></b></td>");
			writer.write("</tr>");
			
			writer.write("<tr width='100%'>");
			writer.write("<td width='30%' align='center'><b><font size='2'>ADULT PASSENGERS</font></b></td>");
			writer.write("<td width='70%' align='center'><b><i><font size='2' color='red'>"
					+ adultsCount + "</font><i></b></td>");
			writer.write("</tr>");
			
			writer.write("<tr width='100%'>");
			writer.write("<td width='30%' align='center'><b><font size='2'>SENIOR PASSENGERS</font></b></td>");
			writer.write("<td width='70%' align='center'><b><i><font size='2' color='red'>"
					+ seniorsCount + "</font><i></b></td>");
			writer.write("</tr>");
			
			writer.write("</table><br>");

			/************************* Table 3 **************************/

			writer.write("<table width='90%' border='1'>");
			writer.write("<tr bgcolor='00ff40' height='35'>");
			writer.write("<th width='20%'><font size='3' color='0000ff'><b>S.NO</b></font></th>");
			writer.write("<th width='45%'><font size='3' color='0000ff'>" + "TESTCASE NAME" + "</font></th>");
			writer.write("<th width='35%'><font size='3' color='0000ff'>"+"STATUS"+"</font></th>");
			writer.write("</tr>");
			writer.write("</table>");
	
			Set<String> testcases=allReports.keySet();
			int count=0;
			for (String testCase : testcases)
			{
				boolean tcStatus=false;
				count++;

				tcStatus=generateIndividualReport(testCase,allReports.get(testCase));
				
				writer.write("<table width='90%' border='1'>");
				writer.write("<tr bgcolor='ffffe6' height='35'>");
				writer.write("<th width='20%'><font size='3' color='0000ff'><b>"+count+"</b></font></th>");
				String href=testCase+".html";
				writer.write("<th width='45%'><font size='3' color='0000ff'><a href='"+href+"'>" + testCase + "</a></font></th>");
				
				if(tcStatus)
				{
					passCount++;
					writer.write(
							"<th width='35%'><font size='3' color='0000ff'><img src='http://img.teapic.com/thumbs/201207/27/110424tgsuxofpjdlbormt.jpg.middle.jpg'  alt='PASS' style='width:25px;height:25px;'/></font></th>");
				}
				else
				{
					failCount++;
					writer.write(
							"<th width='35%'><font size='3' color='0000ff'><img src='http://previews.123rf.com/images/cobalt/cobalt1304/cobalt130400022/19104926-Negate-wrong-icon-red-button--Stock-Vector-delete.jpg'  alt='FAIL' style='width:25px;height:25px;'/></font></th>");
				}
				writer.write("</tr>");
				writer.write("</table>");
				
			}
			
			writer.write("<br>");
			writer.write("<table width='90%' border='1'>");
			writer.write("<tr bgcolor='ffffe6' height='35'>");
			writer.write("<th width='20%'><font size='3' color='0000ff'><b>TOTAL COUNT</b></font></th>");
			writer.write("<th width='20%'><font size='3' color='0000ff'><b>PASS COUNT</b></font></th>");
			writer.write("<th width='20%'><font size='3' color='0000ff'><b>FAIL COUNT</b></font></th>");
			writer.write("</tr>");
			
			writer.write("<tr bgcolor='ffffe6' height='35'>");
			int totalCount=passCount+failCount;
			writer.write("<th width='20%'><font size='3' color='0000ff'><b>"+totalCount+"</b></font></th>");
			writer.write("<th width='20%'><font size='3' color='0000ff'><b>"+passCount+"</b></font></th>");
			writer.write("<th width='20%'><font size='3' color='0000ff'><b>"+failCount+"</b></font></th>");
			writer.write("</tr>");
			
			writer.write("</table>");
		
			writer.write("</center>");
			writer.write("</body>");
			writer.write("</html>");
			writer.close();
			
		} catch (Exception e) {
			System.out.println("Exception in getSummaryReports() method");
			e.printStackTrace();
		}

	}

	/*
	 * Method to generate html report for individual test case 
	 * @reportName :  testcase name for which report is generated
	 * 
	 */
	private static boolean generateIndividualReport(String reportName,List<Result> tcSteps) {
		boolean flag=true;
		try
		{
			Writer writer = null;
			
			int count=0;

			File file = new File("/Users/ctl-user/Documents/AirlineRecent/Output/Reports/"+reportName+".html");
		
			writer = new FileWriter(file, false);
			writer.write("<html><head><style>table, th, td {border-collapse: collapse;border: 1px solid black;}</style><body bgcolor='#ffffff'>");
			writer.write("<center>");
			writer.write("<br><br><br>");

			/************************* Table 1 **************************/

			writer.write(
					"<table width='90%'  border=1><tr bgcolor='violet'><th align='center' width='30%'><img src='http://www.cigniti.com/sites/all/themes/venture_theme/logo.png' alt='CignitiLogo'/></th><th align='center'>"+reportName+" Report</th></tr></table>");
			writer.write("<br>");

			
			/************************* Table 2 **************************/

			writer.write("<table width='90%' border='1'>");
			writer.write("<tr bgcolor='00ff40' height='35'>");
			writer.write("<th width='10%'><font size='3' color='0000ff'><b>STEP NO</b></font></th>");
			writer.write("<th width='30%'><font size='3' color='0000ff'>" + "OPERATION" + "</font></th>");
			writer.write("<th width='45%'><font size='3' color='0000ff'>" + "DESCRIPTION" + "</font></th>");
			writer.write("<th width='25%'><font size='3' color='0000ff'>"+"STATUS"+"</font></th>");
			writer.write("</tr>");
			writer.write("</table>");
			
			int stepCount=0;
			for (Result result : tcSteps)
			{
				
				writer.write("<table width='90%'>");
				writer.write("<tr bgcolor='ffffff' height='30'>");
				writer.write("<th width='10%'><font size='3' >" + result.getStepCount()+ "</font></th>");
				writer.write("<th width='30%'><font size='3' color='0000ff' align='left'>" + result.getOperation()+ "</font></th>");
				writer.write("<th width='45%'><font size='3' align='left'>" + result.getStepDescription() + "</font></th>");
			
				String passImg="http://img.teapic.com/thumbs/201207/27/110424tgsuxofpjdlbormt.jpg.middle.jpg";
				String failImage="http://previews.123rf.com/images/cobalt/cobalt1304/cobalt130400022/19104926-Negate-wrong-icon-red-button--Stock-Vector-delete.jpg";
				if (result.getStatus())
				{
					writer.write("<th width='25%'><img src='"+passImg+"'  alt='PASS' style='width:20px;height:20px;'/></th>");
				}
				else 
				{
					flag=false;
					writer.write("<th width='20%'><a href='"+result.getScreenShotName()+"'><img src='"+failImage+"' alt='FAIL' style='width:20px;height:20px;'/></a></th>");
				}
				writer.write("</tr>");
				writer.write("</table>");
			}

			writer.write("</center>");
			writer.write("</body>");
			writer.write("</html>");
			writer.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	} 

	}
