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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccomodationPage {

	WebDriver driver;

	@FindBy(id = "ss")

	WebElement txtBoxPlace;

	@FindBy(xpath = "//div[@data-mode='checkin']/div/span")

	WebElement linkCalander;

	@FindBy(className = "bui-calendar__content")

	WebElement frameCalander;

	@FindBy(id = "xp__guests__toggle")

	WebElement btnPersons;

	@FindBy(xpath = "//div[@class='xp__button']/div[2]/button")

	WebElement btnSearch;

	@FindBy(xpath = "//div[@id='cross-product-bar']/div/span/span[2]")

	WebElement linkAccomodation;

	@FindBy(id = "hotellist_inner")

	WebElement elelistOfHotels;

	public AccomodationPage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	private void waitForVisibility(WebElement element, int time) throws Error {
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
	}

	public void searchAccomodation() throws Exception {
		waitForVisibility(linkAccomodation, 30);
		linkAccomodation.click();
		txtBoxPlace.sendKeys("Dubai");

		linkCalander.click();
		datePicker(0);
		datePicker(3);

		btnSearch.click();

	}

	public int fetchHotelList() throws Exception {

		waitForVisibility(elelistOfHotels, 15);
		List<WebElement> options = elelistOfHotels.findElements(By.xpath("//div[contains(@id,'hotel_')] "));
		System.out.println(options.size());
		return options.size();
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
