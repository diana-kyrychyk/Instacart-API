package com.instacart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessorImpl implements Processor {

    Logger logger = LoggerFactory.getLogger(ProcessorImpl.class);
    private static final String LOWER = "lower";
    private static final String SAME = "Same";

    public boolean processIsWillingToPay(Integer[] prices, String[] notes,
                                         int priceSensitivity) {
        double totalDiff = 0;
        for (int i = 0; i < prices.length; i++) {
            double itemDiff = calculatePricesDifference(notes[i], prices[i]);
            totalDiff += itemDiff;
        }
        logger.debug("Total price difference of purchase is " + totalDiff);
        boolean isWillingToPay = totalDiff <= priceSensitivity;
        logger.debug("Processing finished successfully, 'isWilLingToPay' equals >{}< \n", isWillingToPay);
        return isWillingToPay;
    }

    private double calculatePricesDifference(String note, int priceOnline) {
        if (note.contains(SAME)) {
            return 0;
        }
        double percentage = parsePercentageDiff(note);
        double priceInStore = calculatePrice(percentage, priceOnline);
        logger.debug("Price in store is {}, whereas price online is {}", priceInStore, priceOnline);
        return priceOnline - priceInStore;
    }

    private double calculatePrice(double percentageDiff, int priceOnline) {
        double percentage = 100.00 + percentageDiff;
        return (priceOnline / percentage) * 100;
    }

    private double parsePercentageDiff(String note) {
        Pattern pattern = Pattern.compile("(\\d+.\\d+)(%) (higher|lower)");
        Matcher m = pattern.matcher(note);
        if (m.find()) {
            String percentage = m.group(1);
            String direction = m.group(3);
            double percentageDiff = Double.parseDouble(percentage);
            if (LOWER.equals(direction)) {
                percentageDiff = percentageDiff * (-1);
            }
            return percentageDiff;
        }
        throw new NoSuchElementException("Not valid: " + note);
    }
}
