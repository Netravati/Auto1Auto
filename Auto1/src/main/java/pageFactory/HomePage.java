package pageFactory;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {

WebDriver driver;
Logger log;    
    //Locator to click on menu
	 @FindBy(xpath="//a[contains(text(),'Jetzt dein Auto finden')]")
	public WebElement searchCar;
	 
	//Locator to click on menu
	@FindBy(id="yearFilter")
	public WebElement yearFilter;
	
	//Locator to click on menu
	@FindBy(id="submit_deliveryarea")
	public WebElement serachBtn;
	
	//Locator all restaurantname
	@FindBy(xpath="(//*[@id='rangeStart'])[2]")
	public WebElement yearRangeStart ;
	
	   
	    //UI locators to create new user account
		 @FindBy(id="sortBy")
		 WebElement sortByPrice;
		 
		 @FindBy(xpath="//div[starts-with(@class,'infoContainer')]/ul/li[1]")
		 List<WebElement> displayedModel;
		 
		 @FindBy(xpath="//div[starts-with(@class,'infoContainer')]/div[1]/div")
		 List<WebElement> displyedPrice;
		 
		 @FindBy(xpath="//div[starts-with(@class,'infoContainer')]/div[1]")
		 List<WebElement> displyedDetails;
		 
	public boolean validateFromYear(int yearStartRange) {
		
		int oldSize = 0;
		int newSize = 0;
		ArrayList<String> cars = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		do {
			oldSize = newSize;
			
			 //Scroll to load new list of cars		
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        try { // to load, could be improved to avoid thread.sleep!
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		  //Iterate through cars listed and validate year and price
		  for(int i=0; i<displyedDetails.size();i++){
			  if(!cars.contains(displyedDetails.get(i).getText()))
				  cars.add(displyedDetails.get(i).getText());
			  
			  String carDetails = displyedDetails.get(i).getText();
			  
			  System.out.println("Car :"+carDetails);
			  log.info("Car :"+carDetails);
			  
			  System.out.println(displyedPrice.get(i).getText().replace(" €", "").replace(".", ""));
			  log.info("Price :"+displyedPrice.get(i).getText().replace(" €", "").replace(".", ""));
			  
			  System.out.println(displayedModel.get(i).getText());
			  log.info("Year/Model :"+displayedModel.get(i).getText());
			  
			  //Validate year filter
			  if(Integer.valueOf(displayedModel.get(i).getText())<yearStartRange){ 
				  System.out.println("Seeing cars before year of regstration start range!");	
				  log.info("Seeing cars before year of regstration start range!");
				  return false;
			  }
			  
			  //Validate price filter
			  if(i<displyedDetails.size()-1) //to avoid AOB exception after last car!
			  if(Integer.valueOf(displyedPrice.get(i).getText().replace(" €", "").replace(".", ""))<Integer.valueOf(displyedPrice.get(i+1).getText().replace(" €", "").replace(".", ""))){
				  System.out.println("Seeing cars price not in filtered order!");	 
				  log.info("Seeing cars price not in filtered order!");
				  return false;
			    
			  }
			  
			  System.out.println("-------------------");
			  log.info("-----------------");
		  }
		  newSize += displayedModel.size();
		  
		  if (displayedModel.size() < 9){ //loads 9 to 12 at a time, we can put this value  in configs 
	        	oldSize = newSize;  // to break the loop once there are no more cars
	        }
		} while(newSize > oldSize);
		
		
		
		
		System.out.println("Totaol number of cars : "+cars.size());
		 log.info("Totaol number of cars : "+cars.size());
		
		return true;
	}
		
			
	 public HomePage(WebDriver driver) {
		   	this.driver = driver;
	        //This initElements method will create all WebElements
	        //PageFactory.initElements(driver, this);
	        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	        log = Logger.getLogger("devpinoyLogger");
	}
	 
	 public void selectRangeStartYear(String year) {
		
		 //yearRangeStart.click();
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOf(yearRangeStart));
		 Select sy = new Select(yearRangeStart);
		 sy.selectByVisibleText(year);
	}
	 public void selectPrice(String option) {
			
		 System.out.println(option);
		 Select sy = new Select(sortByPrice);
		 sy.selectByIndex(2);
	}

}
