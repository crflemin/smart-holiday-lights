package com.developer.pnolanre.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pnolanre on 11/23/2015.
 */
public class Patterns_fragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    Button mPatternA, mPatternB, mPatternC;
    Switch mLightsSwitch;


    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_patterns,parent, false);

        mLightsSwitch = (Switch)v.findViewById(R.id.switch_lights);
        //mLightsSwitch.setChecked(true);
        mLightsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getActivity(),"Turning ON LED Lights",Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getActivity(),"Turning OFF LED lights",Toast.LENGTH_SHORT);
                }

            }
        });
        RadioGroup group = (RadioGroup)v.findViewById(R.id.pattern_group);
        group.setOnCheckedChangeListener(this);

        return v;

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id){
        int patternIndex = translateIdToIndex(id);
    }
    int translateIdToIndex(int id){
        int index = -1;
        switch(id){
            case R.id.pattern_a:
                Toast.makeText(getActivity(),"Turning ON LED Lights",Toast.LENGTH_SHORT);
                index = 0;
                break;
            case R.id.pattern_b:
                index = 1;
                break;
            case R.id.pattern_c:
                index = 2;
                break;
        }
        return index;
    }

}
