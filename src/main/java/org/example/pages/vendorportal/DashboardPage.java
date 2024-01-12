package org.example.pages.vendorportal;

import org.example.pages.AbstractPage;
import org.example.pages.flightreservation.FlightConfirmationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    private final By monthlyEarning = By.xpath("//*[@id=\"monthly-earning\"]");
    private final By annualEarning = By.id("annual-earning");
    private final By profitMargin = By.id("profit-margin");
    private final By inventory = By.id("available-inventory");
    private final By searchField = By.cssSelector("#dataTable_filter input");
    private final By searchResultCount = By.id("dataTable_info");
    private final By userProfile = By.cssSelector("#userDropdown > img");
    private final By logOutLink = By.linkText("Logout");
    private final By logoutModal = By.cssSelector("#logoutModal a");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(monthlyEarning)));
        return driver.findElement(monthlyEarning).isDisplayed();
    }

    public String getMonthlyEarning(){
        return driver.findElement(monthlyEarning).getText();
    }

    public String getAnnualEarning(){
        return driver.findElement(annualEarning).getText();
    }

    public String getProfitMargin(){
        return driver.findElement(profitMargin).getText();
    }

    public String getInventory(){
        return driver.findElement(inventory).getText();
    }

    public void searchOrderHistory(String keyword){
        driver.findElement(searchField).sendKeys(keyword);
    }

    public int searchResult(){
        String results = driver.findElement(searchResultCount).getText();
        String[] arr = results.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Result count is : {}", count);
        return count;
    }

    public void logout(){
        driver.findElement(userProfile).click();
        driver.findElement(logOutLink).click();
        driver.findElement(logoutModal).click();
    }

}
