package com.joemajorheatingandair.joemajorscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

    }

    public void openCalender(View view)
    {
        Intent info = new Intent(this, InformationPage.class);
        startActivity(info);
    }

}
