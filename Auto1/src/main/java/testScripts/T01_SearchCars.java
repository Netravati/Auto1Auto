package testScripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import automationLibrary.DriverClass;
import junit.framework.Assert;
import pageFactory.HomePage;
import pageFactory.Utility;


public class T01_SearchCars{

	WebDriver driver;

	Logger log;

	@BeforeClass
	 public void setup() throws ParseException, InterruptedException, IOException{
			Input in =new Input();
			in.loadEnvConfig();
			log = Logger.getLogger("devpinoyLogger");
			
			
	    }
	 
	 @BeforeMethod
	 public void beforeTestMethod(Method testMethod){
		 
		 driver = new DriverClass().driver;
		 driver.get(Input.url);

		 Utility.logBefore(testMethod.getName());     
	 }
     
	 @AfterMethod(alwaysRun = true)
	 public void takeScreenShot(ITestResult result, Method testMethod) throws InterruptedException {
	 	 if(ITestResult.FAILURE==result.getStatus()){
	 		Utility ut = new Utility(driver);
	 		ut.screenShot(result);
	 	 }
	 	 Utility.clearBrowserCache();
	 	 Utility.logafter(testMethod.getName());
	 	
     }
	@AfterClass(alwaysRun=true)
	public void close() throws InterruptedException{
		driver.quit();
		
				
	}
	
	/* Method : sortByRegYearAndPrice
	 * Author : Netravati Patil
	 * Description : is to filter cars based on year of registration and price in descending order!
	 * 
	*/
	@Test
	public void sortByRegYearAndPrice(){
		
		log.info("Web application Auto1 launched");
		HomePage hp = new HomePage(driver);
		hp.searchCar.click();
		hp.yearFilter.click();
	    
		//Filter cars by year
		log.info("Filter year choosen: "+ Input.selectRangeStartYear);
		hp.selectRangeStartYear(Input.selectRangeStartYear);
		//Filter cars by price descending order
		hp.selectPrice(Input.sortByPrice);
		log.info("Price order choosen : "+ Input.sortByPrice);
		//Validate cars listed
		Assert.assertTrue(hp.validateFromYear(Integer.valueOf(Input.selectRangeStartYear)));
		
		
	}
	/* Method : sortByRegYearAndPriceNegativeTesting
	 * Author : Netravati Patil
	 * Description : is to filter cars based on year of registration in descending order! Negative testing 
	 * 
	*/
	//@Test
	public void sortByRegYearAndPriceNegativeTesingWrongYear(){
		
		log.info("Web application Auto1 launched");
		HomePage hp = new HomePage(driver);
		hp.searchCar.click();
		hp.yearFilter.click();
	    
		//Filter cars by year
		log.info("Filter year choosen: "+ Input.selectRangeStartYear);
		hp.selectRangeStartYear(Input.selectRangeStartYear);
		//Filter cars by price descending order
		hp.selectPrice(Input.sortByPrice);
		log.info("Price order choosen : "+ Input.sortByPrice);
		//Validate cars listed
		Assert.assertTrue(hp.validateFromYear(2020)); //wrong year to validate!
		
		
	}
	/* Method : sortByRegYearAndPriceNegativeTestingWrongPriceOrder
	 * Author : Netravati Patil
	 * Description : is to filter cars based on price in descending order! Negative testing
	 * 
	*/
	//@Test
	public void sortByRegYearAndPriceNegativeTestingWrongPriceOrder(){
		
		log.info("Web application Auto1 launched");
		HomePage hp = new HomePage(driver);
		hp.searchCar.click();
		hp.yearFilter.click();
	    
		//Filter cars by year
		log.info("Filter year choosen: "+ Input.selectRangeStartYear);
		hp.selectRangeStartYear(Input.selectRangeStartYear);
		
		//Do not filter by price to test!
		//hp.selectPrice(Input.sortByPrice);
		
		log.info("Price order choosen : "+ Input.sortByPrice);
		//Validate cars listed
		Assert.assertTrue(hp.validateFromYear(Integer.valueOf(Input.selectRangeStartYear))); 
		
		
	}
}
