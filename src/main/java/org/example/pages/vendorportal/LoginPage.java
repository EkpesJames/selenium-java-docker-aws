package org.example.pages.vendorportal;

import org.example.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    private final By vendorUser = By.id("username");
    private final By vendorPass = By.id("password");
    private final By vendorLoginBtn = By.id("login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(vendorLoginBtn)));
        return driver.findElement(vendorLoginBtn).isDisplayed();
    }

    public void gotTo(String url){
        driver.get(url);
    }

    public void enterVendorLoginDetails(String vendorUserInput, String vendorPassInput){
        driver.findElement(vendorUser).sendKeys(vendorUserInput);
        driver.findElement(vendorPass).sendKeys(vendorPassInput);
    }

    public void clickLoginBtn(){
        driver.findElement(vendorLoginBtn).click();
    }

}