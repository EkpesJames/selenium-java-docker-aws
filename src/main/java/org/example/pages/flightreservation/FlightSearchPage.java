package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class FlightSearchPage extends AbstractPage {

    private final By selectNoOfPassengers = By.id("passengers");
    private final By searchFlightButton = By.id("search-flights");

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(selectNoOfPassengers)));
        return driver.findElement(searchFlightButton).isDisplayed();
    }

    public void selectPassengers(String noOfPassengers){
        Select passengers = new Select(driver.findElement(selectNoOfPassengers));
        passengers.selectByValue(noOfPassengers);
    }

    public void clickSearchButton(){
        WebElement element = driver.findElement(searchFlightButton);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

}