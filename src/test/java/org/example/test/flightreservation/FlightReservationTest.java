package org.example.test.flightreservation;

import org.example.pages.flightreservation.*;
import org.example.test.AbstractTest;
import org.example.test.flightreservation.model.FlightReservationTestDataModel;
import org.example.utils.ConfigFileReader;
import org.example.utils.Constants;
import org.example.utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestDataModel testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setUpParameters(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestDataModel.class);
    }

    @Test
    public void userRegistration(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(ConfigFileReader.get(Constants.FLIGHT_RESERVATION_URL));
        driver.manage().window().maximize();

        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddressDetails(testData.street(), testData.city(), testData.zip());
        registrationPage.clickRegister();
    }

    @Test(dependsOnMethods = "userRegistration")
    public void registrationConfirmationTest(){
        RegistrationConfirmPage registrationConfirmPage = new RegistrationConfirmPage(driver);
        Assert.assertTrue(registrationConfirmPage.isAt());
        Assert.assertEquals(registrationConfirmPage.getFirstName(), testData.firstName());
        registrationConfirmPage.clickGoToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(String.valueOf(testData.passengerCount()));
        flightSearchPage.clickSearchButton();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        SelectFlightPage selectFlightPage = new SelectFlightPage(driver);
        Assert.assertTrue(selectFlightPage.isAt());
        selectFlightPage.selectDepartureFlights();
        selectFlightPage.selectArrivalFlights();
        selectFlightPage.clickConfirmFlightsButton();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmation(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        flightConfirmationPage.getFlightConfirmation();
        String price = flightConfirmationPage.getPrice();
        Assert.assertEquals(price, testData.expectedPrice());
    }

}