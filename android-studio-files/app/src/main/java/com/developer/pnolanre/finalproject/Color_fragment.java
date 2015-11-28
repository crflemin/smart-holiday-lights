package com.developer.pnolanre.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by pnolanre on 11/23/2015.
 */
public abstract class Color_fragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private SeekBar red_bar;
    private SeekBar green_bar;
    private SeekBar blue_bar;
    CustomizationInterface pCustomization;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_color, parent, false);
        pCustomization = (CustomizationInterface)getActivity();
        int red_value,green_value,blue_value = 0;
        red_bar = (SeekBar)findViewById(R.id.red_bar);
        green_bar = (SeekBar)findViewById(R.id.green_bar);
        blue_bar = (SeekBar)findViewById(R.id.blue_bar);
        red_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //int red_value = 0;
            @Override
            public void onProgressChanged(SeekBar red_bar, int progress, boolean userInput) {
                red_value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar red_bar) {
                Toast.makeText(getContext(), "Setting Red Value", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        green_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //int green_value;
            @Override
            public void onProgressChanged(SeekBar green_bar, int progress, boolean userInput) {
                green_value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(),"Setting Green Value",Toast.LENGTH_SHORT).show();;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        blue_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //int blue_value;
            @Override
            public void onProgressChanged(SeekBar blue_bar, int progress, boolean userInput) {
                blue_value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(), "Setting Blue Value", Toast.LENGTH_SHORT).show();
                ;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //pCustomization.setColor(red_value,green_value,blue_value);
        return v;
    }

}
