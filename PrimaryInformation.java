package com.joemajorheatingandair.joemajorscheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PrimaryInformation extends AppCompatActivity {

    private Spinner heatingSystem, coolingSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_information);

        heatingSystem = (Spinner)findViewById(R.id.HeatingSystem);
        coolingSystem = (Spinner) findViewById(R.id.SelectedCooling);

        ArrayAdapter<CharSequence> heatList = ArrayAdapter.createFromResource(this, R.array.HeatingSystemChoices, android.R.layout.simple_spinner_dropdown_item),
                                   coolList = ArrayAdapter.createFromResource(this, R.array.CoolingSystemChoices, android.R.layout.simple_spinner_dropdown_item);

        
    }
}
