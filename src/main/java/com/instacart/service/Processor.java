package com.instacart.service;

public interface Processor {

    boolean processIsWillingToPay(Integer[] prices, String[] notes,
                                  int priceSensitivity);
}
