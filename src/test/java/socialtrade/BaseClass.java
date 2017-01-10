package socialtrade;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass implements Constants{

   public  WebDriver webDriver;
    @BeforeSuite
    @Parameters("browser")
	public void beforeTest(@Optional("firefox")  String browser) {
	System.out.println("Inside before suite");
	    System.setProperty("webdriver.gecko.driver", firefoxpath);
	    System.setProperty("webdriver.chrome.driver", chromdriverpath);
//	    if( browser.equals(chromeBrowser)){
//		webDriver =  new ChromeDriver();
//		
//	    } else if (browser.equals(fireFoxBrowser)){
//		webDriver =  new FirefoxDriver();
//	    } else{	
//		webDriver =  new FirefoxDriver();
//	    }
//	    
	    
	}
   
    
    public WebElement findElement(By by){
	try{
	return webDriver.findElement(by);
    }catch(Exception e){
	e.printStackTrace();
	return  null;
    }
    }	
	public WebElement findElementWithWait(By by,int timeInSeconds){
		try{
		    WebDriverWait wait = new WebDriverWait(webDriver, timeInSeconds, 100);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return webDriver.findElement(by);
	    }catch(Exception e){
		e.printStackTrace();
		return  null;
	    }
	
	}
	
	public WebElement findElementWithWait(By by){
		try{
		    WebDriverWait wait = new WebDriverWait(webDriver, 30, 100);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return webDriver.findElement(by);
	    }catch(Exception e){
		e.printStackTrace();
		return  null;
	    }
	
	}
    
    public void waitForElement(By by, int timeInSeconds) {
	try {
	
	    WebDriverWait wait = new WebDriverWait(webDriver, timeInSeconds, 100);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	} catch (Exception e) {
	   e.printStackTrace();
	}
    }
    
    public  void clickWithWait(By by){
	try{
	findElementWithWait(by).click();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
    
    public void sendKeys(By by,String keys){
	try{
		findElementWithWait(by).sendKeys(keys);;
		}catch(Exception e){
		    e.printStackTrace();
		}
    }
    
    
    public void explicitWait(int time){
	try{
	    Thread.sleep(time);
	}catch(Exception e){
	    e.printStackTrace();
	    
	}
	
    }
    
    
    public void clickOnpopUp(By by,int... time){
	String parentWindowHandler = webDriver.getWindowHandle(); // Store your parent window
	String subWindowHandler = null;

	Set<String> handles = webDriver.getWindowHandles(); // get all window handles
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	}
	webDriver.switchTo().window(subWindowHandler);
	
	if(time.length>0){
	explicitWait(time[0]);
	}else{
	    explicitWait(1000);
	}
	
	clickWithWait(by);
    }
    
    
    public void waitForLoad() {
	System.out.println("Inside page load");
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver webDriver) {
                        return ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(pageLoadCondition);
        System.out.println("Outside page load");
    }
    
    
    public void closeNewWindow() {
	try {
	    String winHandleBefore = webDriver.getWindowHandle();
	    for (String winHandle : webDriver.getWindowHandles()) {
		webDriver.switchTo().window(winHandle);
	 }
	    webDriver.close(); 
	    webDriver.switchTo().window(winHandleBefore);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
    
    
    
    
}
