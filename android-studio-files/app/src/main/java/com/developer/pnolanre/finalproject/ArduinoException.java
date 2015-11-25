package com.developer.pnolanre.finalproject;

/**
 * Created by Chloe on 11/24/2015.
 */

// Use this class to indicate to Activity that something has gone wrong so it can Toast an area
public class ArduinoException extends Exception {

    private static final String sMessage = "Error communicating with arduino.";
    public ArduinoException(){
        super(sMessage);
    }

    public ArduinoException(String message) {
        super(message);
    }
}