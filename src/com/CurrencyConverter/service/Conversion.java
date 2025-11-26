package com.CurrencyConverter.service;

import com.CurrencyConverter.model.Currency;

public class Conversion {
    private Currency currencyA;
    private double amountMoney;
    private Currency currencyB;
    private double conversionResult;
    private String conversionDate;

    public Conversion(){}

    public Conversion(Conversion c){
        this.currencyA = c.currencyA;
        this.amountMoney = c.amountMoney;
        this.currencyB = c.currencyB;
        this.conversionResult = c.conversionResult;
        this.conversionDate = c.conversionDate;
    }

    public Conversion(Currency currencyA, double amountMoney, Currency currencyB, double conversionResult, String conversionDate){
        this.currencyA = currencyA;
        this.amountMoney = amountMoney;
        this.currencyB = currencyB;
        this.conversionResult = conversionResult;
        this.conversionDate = conversionDate;
    }
    public Currency getCurrencyA() {
        return currencyA;
    }

    public Currency getCurrencyB() {
        return currencyB;
    }

    public double getConversionResult() {
        return conversionResult;
    }

    public String getConversionDate() {
        return conversionDate;
    }

    @Override
    public String toString() {
        return "=> "+conversionDate+" | "+currencyA.getCode()+" $"+amountMoney+" = "+currencyB.getCode()+" $"+conversionResult+
                        " | "+currencyA.getName()+" to "+currencyB.getName()+"\n";
    }
}
