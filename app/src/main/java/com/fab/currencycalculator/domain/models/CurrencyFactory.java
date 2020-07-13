package com.fab.currencycalculator.domain.models;

public abstract class CurrencyFactory {

    public static Currency getCurrencyFromCode (String code) {
        if(code.equals(new Bitcoin().getCode())){
            return new Bitcoin();
        }else if(code.equals(new BS().getCode())){
            return new BS();
        }if(code.equals(new Ethereum().getCode())){
            return new Ethereum();
        }if(code.equals(new Euro().getCode())){
            return new Euro();
        }if(code.equals(new Petro().getCode())){
            return new Petro();
        }if(code.equals(new Usd().getCode())){
            return new Usd();
        }else{
            throw new IllegalArgumentException("Not supported currency code: "+code);
        }
    }

}
