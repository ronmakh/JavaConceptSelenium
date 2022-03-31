package com.paynet.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverWrapper {
	protected WebDriver driver;
	
	@BeforeMethod
	@Parameters({"browser"})
	public void setup(@Optional("ch") String browserName) {
		
		if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		
		System.out.println("Launch Browser");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@AfterMethod
	public void teardown() {
		System.out.println("Close Browser");
		driver.quit();
	}
}
