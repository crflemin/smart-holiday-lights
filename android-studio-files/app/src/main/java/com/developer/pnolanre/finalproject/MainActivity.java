package com.developer.pnolanre.finalproject;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends Activity {
    private Button mBluetoothButton;
    private Button mCustomizeButton;
    protected BluetoothAdapter mAdapter;
    private BluetoothTask mThread;
    protected boolean mConnected;
    protected ProgressBar mProgressBar;
    private final int BLUETOOTH_REQUEST_CODE = 42;
    private final String KEY_CONNECTED = "isConnected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mConnected = savedInstanceState.getBoolean(KEY_CONNECTED, false);
        }
        else
            mConnected = false;

        mAdapter = BluetoothAdapter.getDefaultAdapter();

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mBluetoothButton = (Button) findViewById(R.id.bluetooth_button);
        mBluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter != null) {
                    if (!mAdapter.isEnabled()) {
                        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(turnOn, BLUETOOTH_REQUEST_CODE);
                    }
                    else{
                        startBluetoothThread();

                    }
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.bluetooth_unsupported_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCustomizeButton = (Button) findViewById(R.id.customize_button);
        mCustomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnected) {
                    Intent i = new Intent(MainActivity.this, CustomizationActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.customize_failed_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startBluetoothThread(){
        try {

            mThread = new BluetoothTask();
            mThread.execute();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == BLUETOOTH_REQUEST_CODE) {
            startBluetoothThread();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CONNECTED, mConnected);
    }

    private class BluetoothTask extends AsyncTask<Integer, Integer, Boolean> {

        private BluetoothDevice mDevice;
        private BluetoothSocket mSocket;
        // this is a standard UUID for most bluetooth devices, and it happens to work for the arduino's transmitter! :)
        private final UUID DEFAULT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        public BluetoothTask() throws Exception {
            Set<BluetoothDevice> pairedDevices = mAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    mDevice = device;
                }
                mSocket = mDevice.createRfcommSocketToServiceRecord(DEFAULT_UUID);
            }
            else
                throw new ArduinoException("No paired device found.");
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, R.string.connecting,Toast.LENGTH_SHORT).show();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Integer... params){
            try {
                mSocket.connect();
                CustomizationActivity.setArduino(mSocket);
            }
            catch (IOException connectException) {
                try {
                    mSocket.close();
                }
                catch (IOException closeException) {
                    return false;
                }

            }
            catch (ArduinoException aException) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean didConnect) {
            mProgressBar.setVisibility(View.VISIBLE);
            if (didConnect) {
                mConnected = true;
                Toast.makeText(MainActivity.this, R.string.connection_succeeded, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, CustomizationActivity.class);
                startActivity(i);
            }
            else
                Toast.makeText(MainActivity.this, R.string.connection_failed,Toast.LENGTH_SHORT).show();
        }
    }
}