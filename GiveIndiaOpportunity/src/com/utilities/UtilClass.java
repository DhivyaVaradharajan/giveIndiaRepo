package com.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;


public class UtilClass {
	WebDriver driver;
public UtilClass(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

public static void takeScreenShot(WebDriver driver, String FilePath) throws IOException {
	TakesScreenshot scrShot =((TakesScreenshot)driver);	
	File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
	File destFile=new File(FilePath);
	Files.copy(srcFile,destFile);
}
}
