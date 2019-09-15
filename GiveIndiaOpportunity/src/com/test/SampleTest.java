package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.PageObjects;
import com.utilities.UtilClass;

public class SampleTest {
	WebDriver driver=null;
	Properties prop;
	FileInputStream fis;
	Actions act;
	PageObjects pageObjects;
	UtilClass utilClass;

	@BeforeTest
	public void launchBrowser() throws IOException {
		 prop=new Properties();
		 fis =new FileInputStream("../GiveIndiaOpportunity/src/com/test/data.properties");
		 prop.load(fis);
		if(prop.getProperty("browser").contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "../GiveIndiaOpportunity/Drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}else {
			System.setProperty("webdriver.gecko.driver", "../GiveIndiaOpportunity/Drivers/geckodriver_WIN.exe");
			driver=new FirefoxDriver();
		}
		driver.get("https://en.wikipedia.org/wiki/Selenium");
		driver.manage().window().maximize();		
		
	}
	
	@Test
	public void verifyingExternalLinks() throws IOException{
		pageObjects=new PageObjects(driver);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", PageObjects.externalLink);
		for(WebElement e:PageObjects.externalLinks)
		{	
		String completeURL=e.getAttribute("href");
		System.out.println("completeURL is "+completeURL);
		URL url = new URL(completeURL); 
		HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
		connection.setRequestMethod("GET"); 
		connection.connect(); 
		int code = connection.getResponseCode();
		System.out.println(code);
		if(code==(301)|code==(200)) {
		System.out.println("The link "+e.getText()+"  is working");	
		}
		}		
		}
	
	@Test(dependsOnMethods= {"verifyingExternalLinks"})
	public void verifyingOxygenArticle() throws InterruptedException {
		act=new Actions(driver);
		pageObjects=new PageObjects(driver);
		act.moveToElement(PageObjects.oxygenLink).build().perform();
		WebDriverWait explicitWait=new WebDriverWait(driver,5);
		explicitWait.until(ExpectedConditions.visibilityOf(PageObjects.oxygenFeaturedArt));
		Thread.sleep(3000);
		int article=PageObjects.oxygenFeaturedArticle.size();
		Assert.assertTrue(article>=0);		
	}

	@Test(dependsOnMethods= {"verifyingOxygenArticle"})
	public void verifyElementProperties() throws IOException {
		act=new Actions(driver);
		pageObjects=new PageObjects(driver);
		act.moveToElement(PageObjects.elementProperties).build().perform();
		String filePath="../GiveIndiaOpportunity/ScreenShots/screen.png";	
		utilClass=new UtilClass(driver);
		utilClass.takeScreenShot(driver,filePath);//PageObjects.elementProperties
		
	}
	
	@Test(enabled=false)
	public void verifyNoOfPdfsAndPlutonium() {
		pageObjects=new PageObjects(driver);
		System.out.println("No. of Pdfs in Refrences" +pageObjects.noOfPdfs());
		Assert.assertTrue((pageObjects.searchPluto().equals("Plutonium")));
	}
	
	@AfterTest
	public void end() {
		driver.close();
	}
		
}
