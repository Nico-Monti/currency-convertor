package com.CurrencyConverter.service;

import com.CurrencyConverter.model.ApiRequest;
import com.CurrencyConverter.model.SupportedCodes;
import com.CurrencyConverter.util.ReaderWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CurrencyManager {
    private Map<String, String> supportedCurrencyCodes = new HashMap<>();
    private final Stack<Conversion> conversions = new Stack<>();

    private static final int MAX_HOURS = 6;
    private static final String PATH_SUPPORTED_CODES = System.getProperty("user.dir")+"\\supportedCurrencyCodes.json";
    private static final String PATH_CONVERSIONS = System.getProperty("user.dir")+"\\conversions.txt";

    public void getStarted(){
        var request = new ApiRequest();
        try {
            File file = new File(PATH_SUPPORTED_CODES);
            FileReader reader = new FileReader(file);

            long lastModifiedTime = file.lastModified();
            long currentTime = System.currentTimeMillis();
            int milisecondsByDay = 86400000;
            int differenceInHours = (int) (((currentTime-lastModifiedTime)/milisecondsByDay)*24);

            if(differenceInHours >= MAX_HOURS || file.length()==0){
                System.out.println("It seems that the supported currencies codes are outdated. Requesting updated data to the API... ");
                request.supportedCurrencyCodes();
            }

        }catch (FileNotFoundException e){
            System.out.println("Couldn't find any data of the supported currency codes. Requesting to the API... ");
            request.supportedCurrencyCodes();
        }

        supportedCurrencyCodes = this.getSupportedCurrencyCodesMap();
    }

    public Stack<Conversion> getConversions() {
        return conversions;
    }

    public Map<String, String> getSupportedCurrencyCodes() {
        return supportedCurrencyCodes;
    }

    public Map<String,String> getSupportedCurrencyCodesMap() {
        File supportedCodesJSON = new File(PATH_SUPPORTED_CODES);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String supportedCodes = ReaderWriter.readFile(supportedCodesJSON);
        SupportedCodes currencies = gson.fromJson(supportedCodes, SupportedCodes.class);
        this.supportedCurrencyCodes = currencies.asMap();
        return this.supportedCurrencyCodes;
    }

    public void showSupportedCurrencies(){
        if(!this.supportedCurrencyCodes.isEmpty()){
            System.out.println(" Currency code | Currency name");
            supportedCurrencyCodes.forEach((k, v)-> System.out.printf("     [%s]     |  %s%n",k,v));
        } else {
            System.out.println("MAPA VACIO");
        }
        System.out.println();
    }

    public void showConversionsFile(){
        File conversions = new File(PATH_CONVERSIONS);
        String recoveredFile = ReaderWriter.readFile(conversions);
        if(recoveredFile.isEmpty()){
            System.out.println("Try doing some conversions first...");
        }
        System.out.println(recoveredFile);
    }

    public void addConversion(Conversion conversion){
        this.conversions.addFirst(conversion);
    }

    public void saveConversionMade(){
        File conversionsMade = new File(PATH_CONVERSIONS);
        String recoveredFile = ReaderWriter.readFile(conversionsMade);
        ReaderWriter.writeConversionsFile(recoveredFile, this.conversions);
    }

}
