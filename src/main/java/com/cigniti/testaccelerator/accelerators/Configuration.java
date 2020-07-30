package com.cigniti.testaccelerator.accelerators;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cigniti.testaccelerator.utils.UtilitiesClass;

public class Configuration extends UtilitiesClass {
	
	/*public boolean setModuleData(String moduleName,String modulePath,String generateTC,String generateTS)
	{
		boolean result=false;
		try
		{
		
		        
			moduleData.put("moduleName", moduleName);
			moduleData.put("modulePath", modulePath);
			moduleData.put("generateTC", generateTC+"");
			moduleData.put("generateTS", generateTS+"");
			result=true;
		}
		catch(Exception e)
		{
			result=false;
			e.printStackTrace();
		}
		return result;
	}*/
	/*public Map<String, String> getModuleData()
	{
		return moduleData;
	}
	public void createFolders()
	{
		//Need to be implemented
	}*/
	public Map<String, String> loadPropertiesData()
	{
		Map<String, String> props = null;
		InputStream inputStream = null;
		try {
			props = new HashMap<String, String>();
			String fileName = "TestData.properties";
			Properties properties = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException(fileName + " : File not found ");
			}

			props = new HashMap<String, String>();
			
			props.put("ProjectDirectory", properties.getProperty("projectDirectory"));
			props.put("MinPaxCount", properties.getProperty("minPaxCount"));
			props.put("MaxPaxCount", properties.getProperty("maxPaxCount"));
			
			System.out.println("----------ProjectDirectory ---------"+props.get("ProjectDirectory"));
			System.out.println("----------MinPaxCount ---------"+props.get("MinPaxCount"));
			System.out.println("----------MaxPaxCount ---------"+props.get("MaxPaxCount"));
			
			propData=props;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;

	}
	
	public String getProjectDirectory()
	{
		return propData.get("ProjectDirectory");
	}

}
