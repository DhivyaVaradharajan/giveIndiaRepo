package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjects {
	WebDriver driver;
	
	
	  public PageObjects(WebDriver driver) { // TODO Auto-generated constructor
	   this.driver=driver; 
	   PageFactory.initElements(driver, this);
	  }
	 

	@FindBy(xpath="//span[@id='External_links']/../following-sibling::ul//a")
	public static List<WebElement> externalLinks;
	
	@FindBy(xpath="//span[@id='External_links']/../following-sibling::ul//a")
	public static WebElement externalLink;
	
	@FindBy(xpath="//div[@aria-labelledby='Periodic_table_(Large_cells)']//a[@title='Oxygen']/span")
	public static WebElement oxygenLink;
	
	@FindBy(css="a[class='mwe-popups-extract']")
	public static List<WebElement> oxygenFeaturedArticle;
	
	@FindBy(css="a[class='mwe-popups-extract']")
	public static WebElement oxygenFeaturedArt;
	//table[@class='infobox']//tr//a[@title='Periodic table']
	@FindBy(className="infobox")
	public static WebElement elementProperties;
	
	@FindBy(css="div[class*='references']")
	public static WebElement references;
	
	@FindBy(id="searchInput")
	public static WebElement searchBox;
	
	@FindBy(css="div[class='suggestions-result']")
	public static List<WebElement> suggestionPluto;
	
	//@FindBy(css="a[href*='.pdf']") public static List<WebElement> refPdfs;
	 
	public int noOfPdfs() {
		return references.findElements(By.cssSelector("a[href*='.pdf']")).size();
	}
	
	public String searchPluto() {
		searchBox.sendKeys("pluto");
		return suggestionPluto.get(1).getText();
	}

}
