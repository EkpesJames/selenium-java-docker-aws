package org.example.test.vendorportal;

import org.example.pages.vendorportal.DashboardPage;
import org.example.pages.vendorportal.LoginPage;
import org.example.test.AbstractTest;
import org.example.test.vendorportal.model.VendorPortalTestDataModel;
import org.example.utils.ConfigFileReader;
import org.example.utils.Constants;
import org.example.utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestDataModel testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPages(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestDataModel.class);
    }

    @Test
    public void loginTest(){
        loginPage.gotTo(ConfigFileReader.get(Constants.VENDOR_PORTAL_URL));
        driver.manage().window().maximize();
        Assert.assertTrue(loginPage.isAt());
        loginPage.enterVendorLoginDetails(testData.username(), testData.password());
        loginPage.clickLoginBtn();
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashBoardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getInventory(), testData.availableInventory());
        dashboardPage.searchOrderHistory(testData.searchKeyWord());
        Assert.assertEquals(dashboardPage.searchResult(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashBoardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}