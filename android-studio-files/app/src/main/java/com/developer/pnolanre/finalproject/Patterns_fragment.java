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

    private CustomizationInterface mCustomizationListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_patterns,parent, false);
        mCustomizationListener = (CustomizationInterface)getActivity();
        RadioGroup group = (RadioGroup)v.findViewById(R.id.pattern_group);
        group.setOnCheckedChangeListener(this);

        return v;

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id){
        if (radioGroup.findViewById(id).isPressed()) {
            int patternIndex = translateIdToIndex(id);
            mCustomizationListener.setPattern(patternIndex);
        }
    }
    int translateIdToIndex(int id){
        int index = -1;
        switch(id){
            case R.id.pattern_a:
                index = 0;
                break;
            case R.id.pattern_b:
                index = 1;
                break;
            case R.id.pattern_c:
                index = 2;
                break;
            case R.id.pattern_d:
                index = 3;
                break;
            case R.id.pattern_e:
                index = 4;
                break;
            case R.id.pattern_f:
                index = 5;
                break;
            default:
                break;
        }
        return index;
    }

}
