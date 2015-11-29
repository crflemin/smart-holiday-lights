package com.developer.pnolanre.finalproject;
import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.io.OutputStream;

public class ArduinoBluetoothDevice {

    private BluetoothConnection mConnection;
    private int mPatternCount;

    public ArduinoBluetoothDevice(BluetoothSocket socket, int numPatterns) throws ArduinoException {
        mConnection = new BluetoothConnection(socket);
        mPatternCount = numPatterns;
    }

    public void changeColor(int red, int green, int blue) throws ArduinoException {
        String message = "Invalid color value.";

        if (red < 0 || red > 255)
            throw new ArduinoException(message);
        if (green < 0 || green > 255)
            throw new ArduinoException(message);
        if (blue < 0 || blue > 255)
            throw new ArduinoException(message);

        byte[] command = new byte[4];
        command[0] = (byte)'c';
        command[1] = (byte)red;
        command[2] = (byte)green;
        command[3] = (byte)blue;

        mConnection.write(command);
    }

    public void changePattern(int patternIndex) throws ArduinoException {
        if (patternIndex < 0 || patternIndex >= mPatternCount)
            throw new ArduinoException("Invalid pattern selection.");

        byte[] command = new byte[2];
        command[0] = (byte)'p';
        command[1] = (byte)patternIndex;

        mConnection.write(command);
    }

    public void closeConnection() throws ArduinoException {
        mConnection.close();
    }

    // Use this class to encapsulate the Bluetooth connection used by ArduinoInterface
    // This class was inspired by the ConnectionThread example on d.android.com:
    // http://developer.android.com/guide/topics/connectivity/bluetooth.html#ManagingAConnection
    private class BluetoothConnection {
        private final BluetoothSocket mmSocket;
        private final OutputStream mmOutStream;

        public BluetoothConnection(BluetoothSocket socket) throws ArduinoException {
            mmSocket = socket;
            OutputStream tmpOut = null;

            try {
                tmpOut = socket.getOutputStream();
            }
            catch (IOException e) {
                throw new ArduinoException("Failed to establish connection stream.");
            }
            mmOutStream = tmpOut;
        }


        public void write(byte[] bytes) throws ArduinoException {
            try {
                mmOutStream.write(bytes);
            }
            catch (IOException e) {
                throw new ArduinoException("Failed to transmit message.");
            }
        }

        public void close() throws ArduinoException {
            try {
                mmSocket.close();
            }
            catch (IOException e) {
                throw new ArduinoException("Failed to close connection.");
            }
        }
    }
}