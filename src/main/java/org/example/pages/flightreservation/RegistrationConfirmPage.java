package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmPage extends AbstractPage {

    private final By goToFlightSearchButton = By.id("go-to-flights-search");
    private final By firstNameElement = By.cssSelector("#registration-confirmation-section p b");

    public RegistrationConfirmPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(driver.findElement(goToFlightSearchButton)));
        return driver.findElement(goToFlightSearchButton).isDisplayed();
    }

    public String getFirstName(){
        return driver.findElement(firstNameElement).getText();
    }

    public void clickGoToFlightSearch(){
        driver.findElement(goToFlightSearchButton).click();
    }

}