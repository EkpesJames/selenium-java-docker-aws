package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    private final By flightConfirmationElement = By.cssSelector("#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)");
    private final By flightTotalPrice = By.cssSelector("#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)");

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(flightConfirmationElement)));
        return driver.findElement(flightConfirmationElement).isEnabled();
    }

    public String getFlightConfirmation(){
        String confirmation = driver.findElement(flightConfirmationElement).getText();
        log.info("Flight confirmation : {}", confirmation);
        return confirmation;
    }

    public String getPrice(){
        String price = driver.findElement(flightTotalPrice).getText();
        log.info("Total price : {}", price);
        return price;
    }

}