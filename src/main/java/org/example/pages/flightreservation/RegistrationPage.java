package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class RegistrationPage extends AbstractPage {

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("email");
    private final By password = By.id("password");
    private final By street = By.name("street");
    private final By city = By.name("city");
    private final By stateDropDown = By.id("inputState");
    private final By zip = By.name("zip");
    private final By button = By.id("register-btn");

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(firstName)));
        return driver.findElement(firstName).isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void enterUserDetails(String firstNameInput, String lastNameInput){
        driver.findElement(firstName).sendKeys(firstNameInput);
        driver.findElement(lastName).sendKeys(lastNameInput);
    }

    public void enterUserCredentials(String emailInput, String passInput){
        driver.findElement(email).sendKeys(emailInput);
        driver.findElement(password).sendKeys(passInput);
    }

    public void enterAddressDetails(String streetInput, String cityInput, String zipInput){
        driver.findElement(street).sendKeys(streetInput);
        driver.findElement(city).sendKeys(cityInput);
        driver.findElement(zip).sendKeys(zipInput);
    }

    public void clickRegister() {
        driver.findElement(button).click();
    }

}