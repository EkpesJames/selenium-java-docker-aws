package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SelectFlightPage extends AbstractPage {

    private final By departureFlight = By.xpath("//*[@id=\"dep-emirates-economy\"]");
    private final By arrivalFlight = By.xpath("//*[@id=\"arr-emirates-economy\"]");
    private final By confirmFlightsButton = By.xpath("//*[@id=\"confirm-flights\"]");

    public SelectFlightPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(confirmFlightsButton)));
        return driver.findElement(confirmFlightsButton).isDisplayed();
    }

    public void selectDepartureFlights(){
        driver.findElement(departureFlight).click();
    }

    public void selectArrivalFlights(){
        driver.findElement(arrivalFlight).click();
    }

    public void clickConfirmFlightsButton(){
        WebElement element = driver.findElement(confirmFlightsButton);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

}