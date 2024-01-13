package org.example.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utils.ConfigFileReader;
import org.example.utils.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setUoConfig(){
        ConfigFileReader.initialized();
    }

    @BeforeTest
    public void setDriver() throws MalformedURLException {
        this.driver = Boolean.parseBoolean(ConfigFileReader.get(Constants.GRID_ENABLED)) ? getRemoteDriver(): getLocalDriver();
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        return new ChromeDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(ConfigFileReader.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        }
        String urlFormat = ConfigFileReader.get(Constants.BROWSER);
        String hubHost = ConfigFileReader.get(Constants.GRID_HUB_HOST);

        String url = String.format(urlFormat, hubHost);

        log.info("grid url: {}", url);
        return new RemoteWebDriver(new URL(url),capabilities);
    }

    @AfterTest
    public void quiteDriver(){
        driver.quit();
    }

}