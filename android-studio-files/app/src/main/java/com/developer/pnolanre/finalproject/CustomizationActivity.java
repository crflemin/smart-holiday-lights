package com.developer.pnolanre.finalproject;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by pnolanre on 11/23/2015.
 */
public class CustomizationActivity extends FragmentActivity implements CustomizationInterface {

    private static ArduinoBluetoothDevice sArduino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customization_activity);

    }

    public static void setArduino(BluetoothSocket socket) throws ArduinoException {
        sArduino = new ArduinoBluetoothDevice(socket, Color_fragment.NUM_PATTERNS);
        sArduino.changeColor(Color_fragment.DEFAULT_RED, Color_fragment.DEFAULT_GREEN, Color_fragment.DEFAULT_BLUE);
    }

    public void setColor(int red, int green, int blue) {
        try{
            sArduino.changeColor(red, green, blue);
        }
        catch (ArduinoException ae){
            Toast.makeText(CustomizationActivity.this, "Color change did not work", Toast.LENGTH_SHORT).show();

        }


    }

    public void setPattern(int index){
        try{
            sArduino.changePattern(index);
        }
        catch (ArduinoException ae){
            Toast.makeText(CustomizationActivity.this, "Pattern change did not work", Toast.LENGTH_SHORT).show();

        }

    }

}

