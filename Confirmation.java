package com.joemajorheatingandair.joemajorscheduler;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.Manifest.permission_group.SMS;

public class Confirmation extends AppCompatActivity {

    private TextView date, time, creditName, creditNumber, expirationDate, code;
    private final String FRANK_PHONE_NUMBER = "6262972606";
    private final int MESSAGE_CODE = 5;
    private final String[] permissions = {Manifest.permission.SEND_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        date = (TextView) findViewById(R.id.DateChosen);
        time = (TextView) findViewById(R.id.TimeSelected);
        creditName = (TextView) findViewById(R.id.CardName);
        creditNumber = (TextView) findViewById(R.id.CreditCardNumber);
        expirationDate = (TextView) findViewById(R.id.ExpirationDate);
        code = (TextView) findViewById(R.id.SecurityCode);


        date.setText(UnitSelection.info.getMonth() + "/" + UnitSelection.info.getDay() + "/" + UnitSelection.info.getYear());

        String timeFormat = UnitSelection.info.getHour() + ":";
        if(UnitSelection.info.getMinutes() < 10)
            timeFormat += "0" + UnitSelection.info.getMinutes();
        else
            timeFormat += UnitSelection.info.getMinutes();

        time.setText(timeFormat + " " + UnitSelection.info.getAMorPM());

        Intent getName = getIntent();
        creditName.setText(getName.getStringExtra(CreditCardInfo.NAME_ON_CARD));

        String credit = new String(UnitSelection.info.getCreditNumber());
        creditNumber.setText("XXXX-XXXX-XXXX-" + credit.substring(credit.length() - 4, credit.length()));
        expirationDate.setText(UnitSelection.info.getExpiratationMonth() + "/" + UnitSelection.info.getExpiratationYear());
        code.setText(new String(UnitSelection.info.getSecurityCode()));

    }

    public void onStart()
    {
        super.onStart();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
               displayExplaintationMessage();
    }

    private void sendAppointmentSMS()
    {
        String message = "Message from Joe Major app. " + UnitSelection.info.getName() + " " +
                " has a appointment scheduled for " + UnitSelection.info.getMonth() + '/' + UnitSelection.info.getDay()
                + " at " + UnitSelection.info.getHour() + ':';

        if(UnitSelection.info.getMinutes() >= 0 && UnitSelection.info.getMinutes() <= 10)
            message += "0" + UnitSelection.info.getMinutes();
        else
            message += UnitSelection.info.getMinutes();

        message += " " + UnitSelection.info.getAMorPM() + " for a " + UnitSelection.info.getServiceInfo().getService()
                + " of a " + UnitSelection.info.getServiceInfo().getUnitType();

        Intent appSMS = new Intent(this, SMS.getClass());

        PendingIntent send = PendingIntent.getActivity(this, 0, appSMS, 0);
        SmsManager sendToFrank = SmsManager.getDefault();
        sendToFrank.sendTextMessage(FRANK_PHONE_NUMBER, null, message, send, null);
    }

    private void sendAddressSMS()
    {
        String address = "Here is the client info, Name: " + UnitSelection.info.getName() + " Address: "
                         + UnitSelection.info.getStreetNumber() + " " + UnitSelection.info.getStreetName() + "\n"
                         + UnitSelection.info.getCity() + ", CA " + UnitSelection.info.getZipCode() + "\nPhone: "
                         + UnitSelection.info.getPhoneNum();

        if(!UnitSelection.info.getEmail().isEmpty())
           address += " Email: " + UnitSelection.info.getEmail();

        Intent addSMS = new Intent(this, SMS.getClass());

        PendingIntent location = PendingIntent.getActivity(this, 0, addSMS, 0);
        SmsManager sendToFrank = SmsManager.getDefault();
        sendToFrank.sendTextMessage(FRANK_PHONE_NUMBER, null, address, location, null);
    }

    private void sendConfirmSMS()
    {
        String confirm = "Confirmation message from Joe Major App. Your appointment has been scheduled for "
                         + UnitSelection.info.getMonth() + "/" + UnitSelection.info.getDay() + "/"  + UnitSelection.info.getYear()
                         + " at " + UnitSelection.info.getHour() + ":";
        if(UnitSelection.info.getMinutes() < 10)
            confirm += "0" + UnitSelection.info.getMinutes();
        else
            confirm += UnitSelection.info.getMinutes();
        confirm += " " + UnitSelection.info.getAMorPM();

        Intent confirmSMS = new Intent(this, SMS.getClass());
        PendingIntent text = PendingIntent.getActivity(this, 0, confirmSMS, 0);
        SmsManager sendToClient = SmsManager.getDefault();
        sendToClient.sendTextMessage(UnitSelection.info.getPhoneNum(), null, confirm, text, null);
    }

    private void sentCreditInfo()
    {
        String credit = new String(UnitSelection.info.getCreditNumber());

        Intent creditSMS = new Intent(this, SMS.getClass());
        PendingIntent text = PendingIntent.getActivity(this, 0, creditSMS, 0);
        SmsManager sendToFrank = SmsManager.getDefault();
        sendToFrank.sendTextMessage(FRANK_PHONE_NUMBER, null, credit, text, null);
    }

    private void sentCode()
    {
        String code = new String(UnitSelection.info.getSecurityCode());

        Intent codeSMS = new Intent(this, SMS.getClass());
        PendingIntent text = PendingIntent.getActivity(this, 0, codeSMS, 0);
        SmsManager sendToFrank = SmsManager.getDefault();
        sendToFrank.sendTextMessage(FRANK_PHONE_NUMBER, null, code, text, null);
    }

    public void sentAppointmentInfo(View view)
    {
        AlertDialog.Builder confirmPage = new AlertDialog.Builder(Confirmation.this);
        View confirmView = getLayoutInflater().inflate(R.layout.confirmation, null);
        ImageButton exitButton = (ImageButton) confirmView.findViewById(R.id.Exit);

        confirmPage.setView(confirmView);
        final AlertDialog finalMessage = confirmPage.create();
        finalMessage.show();
        sendAppointmentSMS();
        sendConfirmSMS();
        sendAddressSMS();
        sentCreditInfo();
        sentCode();

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalMessage.dismiss();

                Intent begin = new Intent(Confirmation.this, Choice.class);
                startActivity(begin);
            }
        });
    }

    private void displayExplaintationMessage()
    {
        AlertDialog.Builder explain = new AlertDialog.Builder(this);
        View explaination = getLayoutInflater().inflate(R.layout.reasonwhy, null);
        Button accept = (Button) explaination.findViewById(R.id.Accept);

        explain.setView(explaination);
        final AlertDialog alert = explain.create();
        alert.show();

        accept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                alert.dismiss();
                ActivityCompat.requestPermissions(Confirmation.this, permissions, MESSAGE_CODE);
            }
        });
    }
}
