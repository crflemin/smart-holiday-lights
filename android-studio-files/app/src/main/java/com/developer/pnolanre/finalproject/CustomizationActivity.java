package com.developer.pnolanre.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

/**
 * Created by pnolanre on 11/23/2015.
 */
public class CustomizationActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customization_activity);

        //FragmentManager fm = getSupportFragmentManager();
        //Patterns_fragment pf = (Patterns_fragment)fm.findFragmentById(R.id.customization_fragment);
        //Color_fragment cf = (Color_fragment)fm.findFragmentById(R.id.color_fragment);

    }

}
