package org.example.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    public void setDriver() throws MalformedURLException {
        if (Boolean.getBoolean("selenium.grid.enabled")){
            this.driver = getRemoteDriver();
        }else {
            this.driver = getLocalDriver();
        }

    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        return new ChromeDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities;
        if(System.getProperty("browser").equalsIgnoreCase("Chrome")){
            capabilities = new ChromeOptions();
        }else if (System.getProperty("browser").equalsIgnoreCase("firefox")){
            capabilities = new FirefoxOptions();
        }else {
            capabilities = new EdgeOptions();
        }
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
    }

    @AfterTest
    public void quiteDriver(){
        driver.quit();
    }

}