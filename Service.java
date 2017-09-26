package com.joemajorheatingandair.joemajorscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Service extends AppCompatActivity {

    public static final String TYPEOFSERVICE = "Service";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void selectService(View which)
    {
        String service = new String();
        switch(which.getId())
        {
            case R.id.InstallButton:
                service = "Installation";
                break;

            case R.id.ReplaceButton:
                service = "Replacement";
                break;

            case R.id.RepairButton:
                service = "Repair and Maintenance";
                break;
        }

        Intent unitSelect = new Intent(this, UnitSelection.class);
        UnitSelection.isCalender = false;
        unitSelect.putExtra(TYPEOFSERVICE, service);
        startActivity(unitSelect);
    }

    public void goBackToInfo(View view)
    {
        Intent info = new Intent(this, InformationPage.class);
        startActivity(info);
    }

}
