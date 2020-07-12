package com.fab.currencycalculator.data;

public class NoConnectionException extends Exception{

    public NoConnectionException () {
        super("No connection detected");
    }

}
