package com.fab.currencycalculator.domain.models;

public interface Currency {
//    /**
//     * @return the exchange rate for Currency in {@link #getPairCurrency()}
//     */
//    float getRateFromPair ();
//
//    /**
//     * @return Pair Currency to compare with
//     */
//    Currency getPairCurrency();

    /**
     * @return Complete name of currency
     */
    String getName ();

    /**
     * @return Code of the currency
     */
    String getCode ();
}
