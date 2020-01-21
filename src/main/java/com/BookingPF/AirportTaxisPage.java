package com.BookingPF;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AirportTaxisPage {
	WebDriver driver;

	@FindBy(id = "pickupLocation")

	WebElement txtBoxPickUpLocation;

	@FindBy(id = "dropoffLocation")

	WebElement txtBoxDropOffLocation;

	@FindBy(id = "returnJourneyNegative")

	WebElement rbOneWay;

	@FindBy(xpath = "//button[@aria-label='pickup date input field']")

	WebElement linkCalander;

	@FindBy(className = "rw-date-time-picker")

	WebElement frameCalander;

	@FindBy(xpath = "//button[@aria-label='pickup time input field']")

	WebElement linkTime;

	@FindBy(id = "pickupHour")

	WebElement ddPickUpHour;

	@FindBy(id = "pickupMinute")

	WebElement ddPickUpMinute;

	@FindBy(className = "rw-time-picker__confirm")

	WebElement btnConfirm;

	@FindBy(id = "passengers")

	WebElement ddPassengers;

	@FindBy(name = "searchButton")

	WebElement btnSearch;

	@FindBy(xpath = "//div[@id='cross-product-bar']/div/a[4]")

	WebElement linkAirportTaxis;

	@FindBy(className = "gb-c-carousel__items")

	WebElement elelistOfTaxis;

	public AirportTaxisPage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	private void waitForVisibility(WebElement element, int timeInSeconds) throws Error {
		new WebDriverWait(driver, timeInSeconds).until(ExpectedConditions.visibilityOf(element));
	}

	public void searchAirportTaxis(String arg1,String arg2) throws Exception {
		waitForVisibility(linkAirportTaxis, 15);
		linkAirportTaxis.click();

		waitForVisibility(txtBoxPickUpLocation, 20);
		if (!(rbOneWay.isSelected())) {
			rbOneWay.click();
		}
		Thread.sleep(2000);

		txtBoxPickUpLocation.clear();
		txtBoxPickUpLocation.sendKeys(arg1);
		Thread.sleep(7000);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);

		txtBoxDropOffLocation.clear();
		txtBoxDropOffLocation.sendKeys(arg2);
		Thread.sleep(5000);
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		waitForVisibility(linkCalander, 3);
		linkCalander.click();
		datePicker(3);

		waitForVisibility(linkTime, 3);

		linkTime.click();

		if (!(ddPickUpHour.isDisplayed())) {
			linkTime.click();
		}
		Select elePickUpHour = new Select(ddPickUpHour);
		elePickUpHour.selectByVisibleText("09");

		Select elePickUpMinute = new Select(ddPickUpMinute);
		elePickUpMinute.selectByVisibleText("30");

		btnConfirm.click();

		Select elePassengers = new Select(ddPassengers);
		elePassengers.selectByVisibleText("2");

		btnSearch.click();

	}

	public int fetchAirportTaxisList() throws Exception {

		waitForVisibility(elelistOfTaxis, 30);
		List<WebElement> options = elelistOfTaxis.findElements(By.xpath("//div[@class='gb-c-carousel__item']"));
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
