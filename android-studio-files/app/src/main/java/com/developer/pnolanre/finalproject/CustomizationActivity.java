package com.developer.pnolanre.finalproject;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

/**
 * Created by pnolanre on 11/23/2015.
 */
public class CustomizationActivity extends FragmentActivity {

    private static ArduinoBluetoothDevice sArduino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customization_activity);

    }

    public static void setArduino(BluetoothSocket socket) throws ArduinoException {
        sArduino = new ArduinoBluetoothDevice(socket, 3); // TODO: change this to number of radio buttons
        // TODO: remove this - it is just a connection test
        sArduino.changeColor(255, 0, 0);
    }
}
