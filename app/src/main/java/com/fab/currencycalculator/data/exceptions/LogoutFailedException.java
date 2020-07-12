package com.fab.currencycalculator.data.exceptions;

public class LogoutFailedException extends Exception{

    public LogoutFailedException () {
        super("Could not erase Token from device");
    }
}
