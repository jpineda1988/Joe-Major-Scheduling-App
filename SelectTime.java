package com.joemajorheatingandair.joemajorscheduler;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectTime extends AppCompatActivity {

    private final int HOUR_OF_CLOSE = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
    }

    public void onStart()
    {
        super.onStart();

        java.util.Calendar currentDay = java.util.Calendar.getInstance();

        if(UnitSelection.info.getMonth() == (currentDay.get(java.util.Calendar.MONTH) + 1) && UnitSelection.info.getDay() == currentDay.get(java.util.Calendar.DAY_OF_MONTH))
            changeButtonsColors(currentDay.get(java.util.Calendar.HOUR_OF_DAY), currentDay.get(java.util.Calendar.MINUTE), currentDay.get(java.util.Calendar.AM_PM));

    }

    public void openInfoaboutCustomer(View button)
    {
        Button check = (Button) findViewById(button.getId());
        if(check.getCurrentTextColor() == Color.BLACK)
            return;

        switch(button.getId()) {
            case R.id.OperationTime1: {
                UnitSelection.info.setHour(7);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime2: {
                UnitSelection.info.setHour(7);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime3: {
                UnitSelection.info.setHour(8);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime4: {
                UnitSelection.info.setHour(8);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime5: {
                UnitSelection.info.setHour(9);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime6:
            {
                UnitSelection.info.setHour(9);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime7:
            {
                UnitSelection.info.setHour(10);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime8:
            {
                UnitSelection.info.setHour(10);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime9:
            {
                UnitSelection.info.setHour(11);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("AM");
                break;
            }

            case R.id.OperationTime10:
            {
                UnitSelection.info.setHour(11);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime11:
            {
                UnitSelection.info.setHour(12);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime12:
            {
                UnitSelection.info.setHour(12);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime13:
            {
                UnitSelection.info.setHour(1);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime14:
            {
                UnitSelection.info.setHour(1);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime15:
            {
                UnitSelection.info.setHour(2);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime16:
            {
                UnitSelection.info.setHour(2);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime17:
            {
                UnitSelection.info.setHour(3);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime18:
            {
                UnitSelection.info.setHour(3);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime19:
            {
                UnitSelection.info.setHour(4);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime20:
            {
                UnitSelection.info.setHour(4);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime21:
            {
                UnitSelection.info.setHour(5);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime22:
            {
                UnitSelection.info.setHour(5);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime23:
            {
                UnitSelection.info.setHour(6);
                UnitSelection.info.setMinutes(00);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }

            case R.id.OperationTime24:
            {
                UnitSelection.info.setHour(6);
                UnitSelection.info.setMinutes(30);
                UnitSelection.info.setTimeOfDay("PM");
                break;
            }
        }

        Intent customer = new Intent(this, CustomerInformation.class);
        startActivity(customer);
    }

    private int convertStrToInt(String target)
    {
        int number = 0, multiply = 1;
        for(int index = target.length() - 1; index >= 0; index--)
        {
            number += (target.charAt(index) - '0')*multiply;
            multiply *= 10;
        }
        return number;
    }

    private void changeButtonsColors(int hour, int min, int timeOfDay)
    {
        Button button;
        String time, amOrPm;
        int checkHour = 0, checkMin = 0;

        for(int resource = R.id.OperationTime1; resource <= R.id.OperationTime24; resource++)
        {
            button = (Button) findViewById(resource);
            time = button.getText().toString();
            checkHour = Helper.convertStrToNum(time.substring(0, time.indexOf(':')));
            amOrPm = time.substring(time.lastIndexOf(' ') + 1, time.length());

            if(timeOfDay == java.util.Calendar.AM && amOrPm.equals("AM"))
            {
                if(checkHour <= (hour + 1) && min < 60)
                    button.setTextColor(Color.BLACK);
                else
                {
                   checkMin = convertStrToInt(time.substring((time.indexOf(':')) + 1, time.indexOf(' ')));
                   if(checkHour == hour && checkMin <= min)
                     button.setTextColor(Color.BLACK);
                }

                if(checkHour > hour)
                    break;
            }

            if(timeOfDay == java.util.Calendar.PM)
            {
                if(amOrPm.equals("AM"))
                    button.setTextColor(Color.BLACK);
                else
                {
                    if(checkHour < 12)
                        checkHour += 12;

                    if(checkHour >= HOUR_OF_CLOSE)
                    {
                        AlertDialog.Builder noMore = new AlertDialog.Builder(SelectTime.this);
                        View error = getLayoutInflater().inflate(R.layout.checkanotherday, null);

                        Button exit = (Button) error.findViewById(R.id.backtocalender);

                        noMore.setView(error);
                        final AlertDialog message = noMore.create();
                        message.show();

                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent pickAgain = new Intent(SelectTime.this, Calender.class);
                                startActivity(pickAgain);
                            }
                        });
                    }

                    if(checkHour <= (hour + 1) && min < 60)
                        button.setTextColor(Color.BLACK);
                    else
                    {
                        checkMin = convertStrToInt(time.substring((time.indexOf(':') + 1), time.indexOf(' ')));
                        if (checkHour == hour && checkMin <= min)
                            button.setTextColor(Color.BLACK);

                    }
                }

                if(checkHour > hour)
                    break;
            }

        }
    }
}
