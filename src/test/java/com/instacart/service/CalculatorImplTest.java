package com.instacart.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorImplTest {

    Processor readyToPayProcessor = new ProcessorImpl();

    @Test
    void returnTrueReadyToPay_WhenPriceSensitivityIs5_Test() {
        Integer[] prices = {110, 95, 70};
        String[] notes = {"10.0% higher than in-store",
                "5.0% lower than in-store", "Same as in-store"};
        int priceSensitivity = 5;

        boolean actual = readyToPayProcessor.processIsWillingToPay(prices, notes, priceSensitivity);
        assertTrue(actual);
    }

    @Test
    void returnFalseNotReadyToPay_WhenPriceSensitivityIs2_Test() {
        Integer[] prices = {48, 165};
        String[] notes = {"20.0% lower than in-store", "10.0% higher than in-store"};
        int priceSensitivity = 2;

        boolean actual = readyToPayProcessor.processIsWillingToPay(prices, notes, priceSensitivity);
        assertFalse(actual);
    }

    @Test
    void returnTrueReadyToPay_WhenPriceSensitivityis8_Test() {
        Integer[] prices = {80, 15, 130};
        String[] notes = {"10.0% higher than in-store",
                "5.0% lower than in-store", "Same as in-store"};
        int priceSensitivity = 9;
        boolean actual = readyToPayProcessor.processIsWillingToPay(prices, notes, priceSensitivity);
        assertTrue(actual);
    }
}
