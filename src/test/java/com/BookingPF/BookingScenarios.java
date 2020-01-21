package com.BookingPF;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookingScenarios {
	private WebDriver driver;
	public static String url = "https://www.booking.com";
	public static String titleToBeVerified = "Cheap Air Tickets Online, International Flights to India, Cheap International Flight Deals | SpiceJet Airlines";
	public static String urlToBeVerified = "https://book.spicejet.com/Select.aspx";
	AccomodationPage objAccomodation;
	CarRentalsPage objCarRentals;
	AirportTaxisPage objAirportTaxis;
	
	@BeforeClass(alwaysRun = true)
	public void driverInitialization() throws Exception {
		try {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe");

		// Instantiate the web driver and load the page
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-extensions");
		options.addArguments("start-maximized");

		driver = new ChromeDriver(options);

		driver.navigate().to(url);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}}

	/*
	 * Below method searches for available accomodation for given location for the given time frame
	 */
	
	@Test(groups={"Scenario1"})
	public void accomodation() throws Exception {
	
			System.out.println("test");
			objAccomodation = new AccomodationPage(driver);
			objAccomodation.searchAccomodation();
			int listOfHotels = objAccomodation.fetchHotelList();
			System.out.println(listOfHotels);
			Assert.assertTrue(listOfHotels>0, "Accomodations availability");
	}
	
	/*
	 * Below method searches for available car rentals for given location for the given time frame
	 */
	@Test(groups={"Scenario1","Scenario2"})
	public void carRentals() throws Exception {
		
		driver.navigate().to(url);
		objCarRentals = new CarRentalsPage(driver);
		objCarRentals.searchRentalCars();
		int countOfExcellentCars = objCarRentals.fetchCarList("Excellent");
		Assert.assertTrue(countOfExcellentCars>0, "Excellent car rating availability");	
	}
	
	/*
	 * Below method searches for available airport taxis for given location for the given time frame
	 * and passing the data from testng.xml file placed within the project
	 */
	
	@Parameters({"fromPlace","toPlace"})
	@Test(groups={"Scenario2","Scenario3"})
	public void airportTaxis(@Optional("Dubai International Airport") String source,@Optional("Hotel Fairmont The Palm") String destination) throws Exception {
		
		driver.navigate().to(url);
		objAirportTaxis = new AirportTaxisPage(driver);
		objAirportTaxis.searchAirportTaxis(source,destination);
		int countOfAirportTaxis = objAirportTaxis.fetchAirportTaxisList();
		Assert.assertTrue(countOfAirportTaxis>0, "Excellent car rating availability");	
	}
	@AfterClass(alwaysRun = true)
	public void closeDriverSessions() {
		driver.quit();
	}
}
