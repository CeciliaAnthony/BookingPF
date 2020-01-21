package com.BookingPF;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightsPage {
	WebDriver driver;

	@FindBy(name = "origin")

	WebElement txtBoxOrigin;

	@FindBy(name = "destination")

	WebElement txtBoxDestination;

	@FindBy(xpath = "//div[contains(@id,'-depart')]")

	WebElement linkCalander;

	@FindBy(className = "bui-calendar__content")

	WebElement frameCalander;

	@FindBy(xpath = "//label[contains(@id,'-roundtrip-label')]")

	WebElement linkRoundTrip;

	@FindBy(id = "S4KM-travelers")

	WebElement ddTravelers;

	@FindBy(id = "S4KM-cabin-b-label")

	WebElement linkClass;

	@FindBy(xpath = "//button[@id='c1CZ6']/span")

	WebElement btnIncreaseAdults;

	@FindBy(id = "S4KM-submit")

	WebElement btnSearch;

	@FindBy(xpath = "//div[@id='cross-product-bar']/div/a[1]")

	WebElement linkFlights;

	@FindBy(id = "hotellist_inner")

	WebElement elelistOfHotels;

	public FlightsPage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	public void searchFlights() throws Exception {

		linkFlights.click();
		Thread.sleep(3000);
		linkRoundTrip.click();
		Thread.sleep(3000);

		txtBoxOrigin.clear();
		txtBoxOrigin.sendKeys("DEL");
		Thread.sleep(3000);
		txtBoxDestination.click();
		txtBoxDestination.clear();
		txtBoxDestination.sendKeys("DXB");
		Thread.sleep(3000);
		linkCalander.click();
		datePicker(3);
		datePicker(6);
		Thread.sleep(2000);
		ddTravelers.click();

		linkClass.click();

		btnIncreaseAdults.click();
		btnSearch.click();

	}

	public void datePicker(int fromTodayDate) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, fromTodayDate); // Adding days
		String output = sdf.format(c.getTime());
		System.out.println(output);

		String today = output.substring(0, 2);
		;
		System.out.println("Today's number: " + today + "\n");

		// This are the rows of the from date picker table
		List<WebElement> rows = frameCalander.findElements(By.tagName("tr"));

		// This are the columns of the from date picker table
		List<WebElement> columns = frameCalander.findElements(By.tagName("td"));

		for (WebElement cell : columns) {

			// Select Date
			if (cell.getText().equals(today)) {
				cell.click();
				break;
			}
		}

	}
}
