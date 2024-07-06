package Oasys.Oasysapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Appa {
	public WebDriver driver;
	public Select select;
	public WebDriverWait wait;
	public ReadExcelFile readexcel;
	
	
	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Driver//chromedriver.exe");
		 if(driver==null) {
			 driver=new ChromeDriver();
		 }
		 
		driver.manage().window().maximize();
		driver.get("https://cams.odisha.gov.in/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		
	}
	@Test
	public void create_an_account() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Driver//chromedriver.exe");
//		 driver=new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://bimsdemo.demoapplication.in/");
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='btnseed2']")).click();
		
	}
	int count=0;
	@Test(dataProvider="testdata",dataProviderClass=ReadExcelFile.class)
	public void enterFirstname(String username,String pass) throws InterruptedException, IOException
	{
		
		System.out.println("print parameter"+username+" "+pass);
		++count;
		Thread.sleep(1000);
		driver.findElement(By.id("txtFirstName")).sendKeys("rama"+count);
		driver.navigate().refresh();
		
	}
	
	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
//	

}
