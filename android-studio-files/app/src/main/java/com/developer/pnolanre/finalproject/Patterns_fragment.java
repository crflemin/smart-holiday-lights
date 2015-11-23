package com.developer.pnolanre.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pnolanre on 11/23/2015.
 */
public class Patterns_fragment extends Fragment {
    Button mPatternA, mPatternB, mPatternC;
    Switch mLightsSwitch;
    TextView mStatus;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_patterns,parent, false);
        mStatus = (TextView)v.findViewById(R.id.status);

        mPatternA = (Button)v.findViewById(R.id.pattern_a);
        mPatternA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mStatus.setText(R.string.pattern_a);

            }
        });
        mPatternB = (Button)v.findViewById(R.id.pattern_b);
        mPatternB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(),R.string.pattern_a,Toast.LENGTH_SHORT).show();

            }
        });
        mPatternC = (Button)v.findViewById(R.id.pattern_c);
        mPatternC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mStatus.setText("Now Showing pattern C");

            }
        });
        mLightsSwitch = (Switch)v.findViewById(R.id.switch_lights);
        //mLightsSwitch.setChecked(true);
        mLightsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mStatus.setText("Light is on");

                } else {
                    mStatus.setText("Light is off.");

                }

            }
        });



        return v;

    }

}
