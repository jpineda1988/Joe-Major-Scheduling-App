package com.joemajorheatingandair.joemajorscheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class CustomerInformation extends AppCompatActivity {

    private EditText name, address, phone, city, zipcode, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);

        View current = findViewById(R.id.customerinfo);
        current.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    Helper.hidekeyboard(view, CustomerInformation.this);
            }
        });
    }

    private void collectedInformation()
    {
        int code = 0, street = 0;

        UnitSelection.info.setName(name.getText().toString());
        String completeAddress = address.getText().toString(), streetNum, streetName;
        streetNum = getStreetNumber(completeAddress);
        streetName = getStreetName(completeAddress, streetNum);

        street = convertStrToNum(streetNum);
        UnitSelection.info.setStreetNumber(street);
        UnitSelection.info.setStreetName(streetName);
        UnitSelection.info.setCity(city.getText().toString());

        code = convertStrToNum(zipcode.getText().toString());
        UnitSelection.info.setZipCode(code);

        UnitSelection.info.setPhoneNum(phone.getText().toString());
        UnitSelection.info.setEmail(email.getText().toString());
    }

    private String getStreetNumber(String whole)
    {
        String number;
        int count = 0;

        for(int index = 0; index < whole.length() && whole.charAt(index) != ' '; index++)
        {
           if(isNumeric(whole.charAt(index)))
               count++;
        }
        number = whole.substring(0, count);
        return number;
    }

    private String getStreetName(String whole, String number)
    {
        String theRest = whole.substring(number.length());
        return theRest;
    }

    private int convertStrToNum(String str)
    {
        int number = 0, multiply = 1;
        for(int index = str.length() - 1; index >= 0; index--)
        {
            number += convertCharToNum(str.charAt(index))*multiply;
            multiply *= 10;
        }
        return number;
    }

    private boolean isNumeric(char character)
    {
        return (character >= '0') && (character <= '9');
    }

    private int convertCharToNum(char character)
    {
        if(isNumeric(character))
            return character - '0';
        return -1;
    }

    public void openCredit(View view)
    {
        collectedInformation();
        if(checkEnteredInformation())
        {
            Intent credit = new Intent(this, CreditCardInfo.class);
            startActivity(credit);
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View required = getLayoutInflater().inflate(R.layout.lackofinfo, null);
            Button accept = (Button) required.findViewById(R.id.Continue);

            alert.setView(required);
            final AlertDialog lack = alert.create();
            lack.show();

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lack.dismiss();
                }
            });
        }
    }

    private boolean checkEnteredInformation()
    {
        return (!name.getText().toString().isEmpty() && !address.getText().toString().isEmpty() && !phone.getText().toString().isEmpty() &&
                !city.getText().toString().isEmpty() && !zipcode.getText().toString().isEmpty());
    }

    private void hidekeyboard(View v)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void onStart()
    {
        super.onStart();
        name = (EditText) findViewById(R.id.Name);
        address = (EditText) findViewById(R.id.HomeAddress);
        city = (EditText) findViewById(R.id.CityName);
        zipcode = (EditText) findViewById(R.id.ZipCode);
        phone = (EditText) findViewById(R.id.PhoneNumber);
        email = (EditText) findViewById(R.id.EmailAddress);

        if(!UnitSelection.info.getName().isEmpty())
            name.setText(UnitSelection.info.getName());

        int streetNum = UnitSelection.info.getStreetNumber();
        if(streetNum != 0 && !UnitSelection.info.getStreetName().isEmpty())
            address.setText(streetNum + UnitSelection.info.getStreetName());

        if(!UnitSelection.info.getCity().isEmpty())
            city.setText(UnitSelection.info.getCity());

        int zip = UnitSelection.info.getZipCode();
        if(zip != 0)
            zipcode.setText(UnitSelection.info.getZipCode() + "");

        if(!UnitSelection.info.getPhoneNum().isEmpty())
            phone.setText(UnitSelection.info.getPhoneNum());

        if(!UnitSelection.info.getEmail().isEmpty())
            email.setText(UnitSelection.info.getEmail());

    }
}
