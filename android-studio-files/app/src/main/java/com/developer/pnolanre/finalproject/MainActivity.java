package com.developer.pnolanre.finalproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button mBluetooth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBluetooth = (Button) findViewById(R.id.bluetooth_cnt);
        mBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(MainActivity.this, CustomizationActivity.class);
                //startActivity(i);
                Toast.makeText(MainActivity.this, "awesome", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, CustomizationActivity.class);
                startActivity(i);

            }
        });


    }

}
