package org.example.test.flightreservation.model;

public record FlightReservationTestDataModel(
        String firstName,
        String lastName,
        String email,
        String password,
        String street,
        String city,
        String zip,
        int passengerCount,
        String expectedPrice) {
}