package com.joemajorheatingandair.joemajorscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class UnitSelection extends AppCompatActivity {

    public static AppointmentInfo info = new AppointmentInfo();
    private ServiceInfo serve;
    private String title;
    private TextView display;
    public static boolean isCalender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_selection);

        serve = new ServiceInfo();
    }

    public void openToCalender(View view)
    {
        serve.setService(title);
        switch(view.getId())
        {
            case R.id.internalbutton:
                serve.setTypeOfUnit("Internal");
                break;

            case R.id.outerbutton:
                serve.setTypeOfUnit("Outer");
                break;

            case R.id.SplitButton:
                serve.setTypeOfUnit("Split duct");
                break;

            case R.id.ThemostatButton:
                serve.setTypeOfUnit("Themostat");
                break;

            case R.id.CeilingButton:
                serve.setTypeOfUnit("Ceiling Cassette");
                break;

            case R.id.PackageButton:
                serve.setTypeOfUnit("Package Unit");
                break;

            case R.id.Rooftop:
                serve.setTypeOfUnit("Rooftop Air Conditioner");
                break;

            case R.id.TowerAir:
                serve.setTypeOfUnit("Tower Air Conditioner");
                break;

            default:
                serve.setTypeOfUnit("Other");
                break;
        }

        info.setService(serve);
        Intent calender = new Intent(this, Calender.class);
        startActivity(calender);
    }

    public void goBackToService(View back)
    {
        Intent goBack = new Intent(this, Service.class);
        startActivity(goBack);
    }

    public void onStart()
    {
        super.onStart();

        if(!isCalender)
        {
            Intent titleIntent = getIntent();
            title = titleIntent.getStringExtra(Service.TYPEOFSERVICE);
        }
        else
            title = UnitSelection.info.getServiceInfo().getService();
        display = (TextView) findViewById(R.id.Title);
        display.setText(title);
    }
}



