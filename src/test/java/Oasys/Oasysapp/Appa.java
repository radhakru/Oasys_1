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
		driver.get("http://bimsdemo.demoapplication.in/");
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
		driver.findElement(By.xpath("//a[text()='Create an account']")).click();
		
	}
	int count=0;
	@Test(dataProvider="testdata",dataProviderClass=ReadExcelFile.class)
	public void enterFirstname(String id,String firstname,String lastname,String District,String block,String policestattion,String village,String phoneno,String email) throws InterruptedException, IOException
	{
		
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//input[@name='FirstName']")).clear();
//		Thread.sleep(3000);
		System.out.println(firstname);
		 wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='FirstName']")));
		
		driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys(firstname);
		
		/* Thread.sleep(2000); */
		 wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lName']")));
		driver.findElement(By.xpath("//input[@id='lName']")).sendKeys(lastname);
		
//		Thread.sleep(2000);
		wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='eMail']")));
		driver.findElement(By.xpath("//input[@id='eMail']")).sendKeys(email);
		
		
		System.out.println(phoneno);
		
//		Thread.sleep(3000);
		wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mNo']")));
		driver.findElement(By.xpath("//input[@id='mNo']")).sendKeys(phoneno);
		
//		Thread.sleep(2000);
		wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtAdharNo']")));
		driver.findElement(By.xpath("//input[@id='txtAdharNo']")).sendKeys("123456789012");
		
		Thread.sleep(2000);


	System.out.println(District.substring(0,District.length()-2));
	String d=District.substring(0,District.length()-2);
		WebElement district=driver.findElement(By.xpath("//*[@id='ddlDistrict']"));
		wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ddlDistrict']")));
		select=new Select(district);
		select.selectByValue(d);
		
		Thread.sleep(2000);
		String b=block.substring(0,block.length()-2);
		WebElement blockname=driver.findElement(By.xpath("//*[@id='ddlBlock']"));
		wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ddlBlock']")));
		select=new Select(blockname);
		select.selectByValue(b);
		
		Thread.sleep(2000);
		String p=policestattion.substring(0,policestattion.length()-2);
		System.out.println(policestattion);
		
		WebElement policestation=driver.findElement(By.xpath("//*[@id='ddlPolice']"));
		wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ddlPolice']")));
		select=new Select(policestation);
		select.selectByValue(p);
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id='txtArea']")).sendKeys(village);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='txtVillage']")).sendKeys("Test");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='txtPin']")).sendKeys("123456");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='pWd']")).sendKeys("Oasys@1234");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='cPwd']")).sendKeys("Oasys@1234");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='submitButton']")).click();
		Thread.sleep(5000);
		count++;
		
		
		
		
		String confirmation_message=driver.findElement(By.xpath("//*[@class='swal2-popup swal2-modal swal2-show']/div[2]")).getText();
		System.out.println("print  message ="+confirmation_message);
		if(confirmation_message.equals("Contact number is already in use.")) {
			
			driver.findElement(By.xpath("//*[text()='OK']")).click();
			
		}
		else {
			driver.findElement(By.xpath("//*[text()='OK']")).click();
		}
		
	
		
		Thread.sleep(2000);
		driver.navigate().refresh();
		
		
		//create object of readexcelfile
		readexcel=new ReadExcelFile();
		readexcel.inserAndEditCell(confirmation_message,count);

		
//		FileOutputStream fis=new FileOutputStream(System.getProperty("user.dir")+"//Externaldata//Data3.xlsx");
		
		
		
		
		

		
		
	}
	
	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
//	

}
