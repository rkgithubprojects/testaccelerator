package com.cigniti.testaccelerator.reports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

public class TestReports {

	
	public static void main(String[] args) throws IOException {
		TestReports tr=new TestReports();
	//	tr.getLinkNames();
		
		
		tr.generateReport(tr.getLinkNames());
		
		
		
		
	}
	
	
	public Set<String> getLinkNames()
	{
		Set<String> links=new LinkedHashSet<>();
		links.add("Booking_1");
		links.add("Booking_2");
		links.add("Booking_3");
		links.add("Booking_4");
		links.add("Booking_5");
		links.add("Booking_6");
		return links;
	}
	
	public void generateReport(Set<String> links) throws IOException
	{
		Writer writer = null;
		int count=0;

		File file = new File("/Users/ctl-user/Documents/AirlineRecent/ScreenShots/TestReports.html");
		String path = "/Users/ctl-user/Documents/AirlineRecent/Images";

		writer = new FileWriter(file, false);
		
		writer.write("<html>");
		writer.write("<body>");
		writer.write("<table>");
		
		writer.write("<tr>");
		writer.write("<td>SNO</td>");
		writer.write("<td>TestCase Name</td>");
		writer.write("<td>Status</td>");
		writer.write("</tr>");
		int i=0;
		for (String string : links) {
			i=i+1;
			writer.write("<tr>");
			writer.write("<td>"+i+"</td>");
			writer.write("<td><a href='/Users/ctl-user/Documents/AirlineRecent/ScreenShots/SummaryReports.html'>"+string+"</a></td>");
			writer.write("<td>PASS</td>");
			writer.write("</tr>");
		}
		
		
		
		writer.write("</table>");
		writer.write("</body>");
		writer.write("</html>");
		writer.close();
	}
}
