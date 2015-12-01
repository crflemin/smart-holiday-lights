package com.developer.pnolanre.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by pnolanre on 11/23/2015.
 */
public class Color_fragment extends Fragment {
    private SeekBar red_bar;
    private SeekBar green_bar;
    private SeekBar blue_bar;
    private Button color_button;
    private CustomizationInterface mCustomizationListener;
    public int red_value, green_value, blue_value;
    public static final int DEFAULT_RED = 125;
    public static final int DEFAULT_GREEN = 0;
    public static final int DEFAULT_BLUE = 125;
    public static final int NUM_PATTERNS = 6;
    public static final String KEY_RED="red";
    public static final String KEY_GREEN="green";
    public static final String KEY_BLUE="blue";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_color, parent, false);
        mCustomizationListener = (CustomizationInterface) getActivity();

        red_bar = (SeekBar) v.findViewById(R.id.red_bar);
        red_bar.setProgress(DEFAULT_RED);
        if (savedInstanceState != null)
            red_value = savedInstanceState.getInt(KEY_RED, DEFAULT_RED);
        else
            red_value = DEFAULT_RED;

        green_bar = (SeekBar)v.findViewById(R.id.green_bar);
        green_bar.setProgress(DEFAULT_GREEN);
        if (savedInstanceState != null)
            green_value = savedInstanceState.getInt(KEY_GREEN, DEFAULT_GREEN);
        else
            green_value = DEFAULT_GREEN;

        blue_bar = (SeekBar)v.findViewById(R.id.blue_bar);
        blue_bar.setProgress(DEFAULT_BLUE);
        if (savedInstanceState != null)
            blue_value = savedInstanceState.getInt(KEY_BLUE, DEFAULT_BLUE);
        else
            blue_value = DEFAULT_BLUE;

        color_button = (Button)v.findViewById(R.id.color_button);
        color_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendColor();
            }
        });

        red_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar red_bar, int progress, boolean userInput) {
                red_value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar red_bar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        green_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar green_bar, int progress, boolean userInput) {
                green_value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        blue_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar blue_bar, int progress, boolean userInput) {
                blue_value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        return v;
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_RED, red_value);
        savedInstanceState.putInt(KEY_GREEN, green_value);
        savedInstanceState.putInt(KEY_BLUE, blue_value);
    }

    public void sendColor() {
        mCustomizationListener.setColor(red_value, green_value, blue_value);
    }

}


