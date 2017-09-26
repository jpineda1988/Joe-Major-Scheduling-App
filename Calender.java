package com.joemajorheatingandair.joemajorscheduler;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

import java.util.Calendar;


public class Calender extends AppCompatActivity  {

    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        calendar = (CalendarView) findViewById(R.id.calender);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                java.util.Calendar selectDate = java.util.Calendar.getInstance(), currentDate = java.util.Calendar.getInstance();
                selectDate.set(year, month, day);

                if(selectDate.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SUNDAY)
                {
                    createDialog(Calender.this, R.layout.closeonsundays, R.id.Exit);
                    return;
                }

                if(day < currentDate.get(Calendar.DAY_OF_MONTH) && month <= currentDate.get(Calendar.MONTH))
                {
                    createDialog(Calender.this, R.layout.datenotavailable, R.id.ExitButton);
                    return;
                }


                Intent timeSelect = new Intent(Calender.this, SelectTime.class);
                UnitSelection.info.setMonth(month + 1);
                UnitSelection.info.setDay(day);
                UnitSelection.info.setYear(year);
                startActivity(timeSelect);

            }
        });


    }

    private void createDialog(Context con, int resource, int buttonResource)
    {
        AlertDialog.Builder build = new AlertDialog.Builder(con);
        View errorMessage = getLayoutInflater().inflate(resource, null);

        ImageButton exit = (ImageButton) errorMessage.findViewById(buttonResource);

        build.setView(errorMessage);
        final AlertDialog error = build.create();
        error.show();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error.dismiss();
            }
        });
    }
}
