package testScripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import configsAndTestData.ConfigLoader;
import configsAndTestData.ConfigMain;
import configsAndTestData.Environment;
import configsAndTestData.TestData;




import pageFactory.Utility;

public class Input {

	

	Logger log;
	//Config and test data files---------------------------------
	public static ConfigMain config;
	public static Environment envConfig;
	public static TestData testData;
	//ConfigMain data---------------------------------------------
	
	public static  String suite;
	public static String browserName;
	public static  String screenShotOnPass;
	public static  String screenShotOnFail;
	public static int wait30;
	public static int wait60;
	public static int wait90;
	public static int wait120;
	public static int interval;
	
	//Environment data---------------------------------------------
	public static String url;

	
	//test data------------------

	public static String sortByPrice;
	public static String selectRangeStartYear;
	
    
	@BeforeSuite(alwaysRun=true)
	public void loadEnvConfig() throws ParseException, InterruptedException, IOException {
		log = Logger.getLogger("devpinoyLogger");
		log.info("Before Suite is called");
		
		//Common Data-------------------------------------------------------------
		config = (ConfigMain) new ConfigLoader().load("ConfigMain");
		envConfig = (Environment) new ConfigLoader().load(config.env);
		log.info("Running scripts on "+config.env);
		
	
		suite = config.suite;
		browserName = config.browserName;
		screenShotOnPass = config.screenShotOnPass;
		screenShotOnFail = config.screenShotOnFail;
		
		wait30 = config.wait30;
		wait60 = config.wait60;
		wait90 = config.wait90;
		wait120 = config.wait120;
		
		
		
		//Environment data-------------------------------------------------------------
		url=envConfig.url;
	

		
		//Test data-------------------------------------------------------------
		
		
		testData = (TestData) new ConfigLoader().load("TestData");
		
		
		 sortByPrice=testData.sortByPrice;
		 selectRangeStartYear=testData.selectRangeStartYear;
	
		 log.info("Test Data is loaded");
		
		
		
	}
	
	

	
}
