package com.cigniti.testaccelerator.exe;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaTPoint {

	public static void main(String[] args) {
		
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.get("http://www.javatpoint.com/");
		driver.manage().window().maximize();
		
		boolean verifyHomePage=driver.getTitle().contains("Tutorials - Javatpoint");
		//Assert.assertTrue("Java T Point page is not displayed",verifyHomePage);
		System.out.println("Java T Point Home page is verified");
		
		List<WebElement> topicsCovered =driver.findElements(By.xpath("//li/a"));
		System.out.println("Display all the topis covered in this website");
		for (WebElement topic : topicsCovered) {
			System.out.println("Topic : "+topic.getText());
		}
		
		
		System.out.println("Navigate to Java Tutorial Page ");
		driver.findElement(By.xpath("//li/a[@href='java-tutorial']")).click();
		
		System.out.println("Verify Java Tutorials Page is Displayed");
		boolean verifyJavaPage=driver.getTitle().contains("Java Tutorial");
		//Assert.assertTrue("Java Tutorial Page is not displayed", verifyJavaPage);
		
		
		System.out.println("Verify If Exception Handling feature is covered in Java Tutorial");
		WebElement exceptionHandling=driver.findElement(By.xpath("//div[@class='leftmenu2']//a[@href='exception-handling-in-java']"));
		//Assert.assertTrue("Exception Handling topic is not covered in Java Tutorial",exceptionHandling!=null);
		
		System.out.println("Navigate to Exception Handling Page in Java Tutorial");
		exceptionHandling.click();
		
		System.out.println("Verify Exception Handling page is Displayed");
		//Assert.assertTrue("Exception Handling PAge is not displayed",driver.getTitle().contains("Exception Handling"));
		
		
		System.out.println("----- Display the topics covered in Exception Handling -----");
		List<WebElement> topicsInExceptions =driver.findElements(By.xpath("//div[@id='upr']//ol//li"));
		for (WebElement extopic : topicsInExceptions) {
			System.out.println("Exception Handling Topic : "+extopic.getText());
		}
		
		//close the driver
		driver.quit();
		
	}
}
