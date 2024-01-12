package org.example.test.vendorportal.model;

public record VendorPortalTestDataModel(
        String username,
        String password,
        String monthlyEarning,
        String annualEarning,
        String profitMargin,
        String availableInventory,
        String searchKeyWord,
        int searchResultsCount) {
}