package com.fab.currencycalculator.data.exceptions;

public class NoConnectionException extends Exception{

    public NoConnectionException () {
        super("No connection detected");
    }

}
