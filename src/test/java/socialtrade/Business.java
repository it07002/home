package socialtrade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Business extends BaseClass  {
    
    
    
    @Test(dataProvider = "getUser",threadPoolSize = 3)
    public void busin(String username,String pwd){
	try{
	
	WebDriver webDriver = new ChromeDriver();
	BaseClass baseClass  =  new BaseClass();
	baseClass.webDriver=webDriver;
	webDriver.get("https://socialtrade.biz");
	baseClass.waitForLoad();
	webDriver.manage().window().maximize();
	
	baseClass.clickOnpopUp(By.cssSelector("img.close-image"));
	baseClass.sendKeys(By.id("txtEmailID"), username);
	baseClass.sendKeys(By.id("txtPassword"), pwd);
	baseClass.explicitWait(1000);
	baseClass.clickWithWait(By.id("CndSignIn"));
	baseClass.waitForLoad();
	baseClass.explicitWait(1000);
	baseClass.clickOnpopUp(By.cssSelector("img.close-image"),2000);
	baseClass.explicitWait(1000);
	baseClass.clickWithWait(By.xpath("//a[text()='View Advertisements']"));
	baseClass.waitForLoad();
	baseClass.explicitWait(1000);
	List<WebElement> ads =  webDriver.findElements(By.xpath(".//*[contains(@id,'pending')]/../../td/span[contains(@id,'hand')]/i"));
	 System.out.println(username+":"+ads.size());
	 
	for (WebElement webElement : ads) {
	  
	    JavascriptExecutor jse = (JavascriptExecutor) webDriver;
	    jse.executeScript("window.scrollBy(0,250)", "");
	    baseClass.  explicitWait(1000);
	    webElement.click();
	    
	    baseClass.explicitWait(30000);
	    System.out.println(System.currentTimeMillis());
	} 
	
	System.out.println("Completed for :" + username );
	
	}catch(Exception e){
	    e.printStackTrace();
	}
	
	
    }
    
    
    
    @DataProvider(parallel=true)
    public Object[][] getUser(){
    
     
     Map<String,String> map = new HashMap<String,String>();
     
	map.put("62209933", "akshya@1234");
//	map.put("61775051", "akshya@1234");
//	map.put("61775196", "akshya@1234");
//	map.put("61778014", "akshya@1234");
//	map.put("61983397", "akshya@1234");
//	map.put("61895043", "akshya@1234");
	map.put("61984244", "akshya@1234");
//	map.put("62188870", "akshya@1234");
     
   
     Object data[][] = new Object[map.size()][2];
     int i=0;
     for (String id: map.keySet()) {
	data[i][0]=id;
	data[i][1]=map.get(id);
	i++;
    }
   
    
     
     return data;
    } 
    

    
    
}
