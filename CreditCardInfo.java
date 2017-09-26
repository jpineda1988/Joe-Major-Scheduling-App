package com.joemajorheatingandair.joemajorscheduler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class CreditCardInfo extends AppCompatActivity {

    private final char firstJCBNumber[] = {'1', '8', '0', '0'}, firstenRoute[] = {'2','0','1','4'},
                      secondJCBNumber[] = {'2','1','3','1'}, secondEnRoute[] = {'2','1','4','9'}, carteBlanche[] = {'3','0'}, discover[] = {'6','0','1','1'};
    private final int JCB = 3, VISA = 4, MASTERCARD = 5, AMERICANEXPRESS = 4, INTERNATIONAL = 6, AMERICANEXPRESS2 = 7,
                      CARTEBLANCHE = 8;
    private String eMonth, eYear;
    private TextView issuerName;
    private Spinner month, year;
    private EditText name, credit, code;
    public static final String NAME_ON_CARD = "Name";
    public static boolean haveInfo = false, alreadyDisplayed = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_info);

        month = (Spinner) findViewById(R.id.Month);
        ArrayAdapter<CharSequence> expirationMonth = ArrayAdapter.createFromResource(this, R.array.MonthOfTheYear, android.R.layout.simple_spinner_item),
                                   expirationYear = ArrayAdapter.createFromResource(this, R.array.Years, android.R.layout.simple_spinner_item);

        expirationMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(expirationMonth);

        year = (Spinner) findViewById(R.id.Year);
        expirationYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(expirationYear);

        View credit = findViewById(R.id.activity_credit_card_info);
        credit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    Helper.hidekeyboard(view, CreditCardInfo.this);
            }
        });
    }

    public void onStart()
    {
        super.onStart();
        name = (EditText) findViewById(R.id.PersonName);
        credit = (EditText) findViewById(R.id.CreditCardNumber);
        code = (EditText) findViewById(R.id.Code);

        if(haveInfo)
        {
            Intent getName = getIntent();
            name.setText(getName.getStringExtra(CreditCardInfo.NAME_ON_CARD));
            String number = new String(UnitSelection.info.getCreditNumber()),
                    cvv = new String(UnitSelection.info.getSecurityCode());
            credit.setText(number);
            code.setText(cvv);
        }

        if(!alreadyDisplayed)
            showCustomerNotice();

    }

    private boolean checkCreditCardNumber(String cardNumber)
    {
        boolean isValid = false;

        int[] credit = convertCharArrayToNumber(cardNumber);
        int checkDigit = credit[credit.length - 1], sum = 0, evenDigit = 0;

        for(int index = credit.length - 2; index >= 0; index--)
        {
            if(isEven(index))
            {
                evenDigit = credit[index]*2;
                if(evenDigit >= 10)
                {
                    sum += evenDigit%10;
                    sum += evenDigit/10;
                }
                else
                    sum += evenDigit;
            }
            else
                sum += credit[index];

        }

        sum *= 9;
        if(sum%10 == checkDigit)
            isValid = true;
        return isValid;
    }

    private int[] convertCharArrayToNumber(String check)
    {
        int[] number = new int[check.length()];

        for(int index = 0; index < check.length(); index++)
            number[index] = check.charAt(index) - '0';
        return number;
    }

    private boolean isEven(int number)
    {
        return (number%2 == 0);
    }

    private boolean isNumeric(char character)
    {
        return (character >= '0') && (character <= '9');
    }


    public void confirmAppointment(View view)
    {
        if(!credit.getText().toString().isEmpty())
          UnitSelection.info.setCreditNumber(credit.getText().toString());
        else
        {
            createDialog(CreditCardInfo.this, R.layout.need_credit, R.id.Exit);
            return;
        }

        if(!code.getText().toString().isEmpty())
          UnitSelection.info.setSecurityCode(code.getText().toString());
        else
        {
            createDialog(CreditCardInfo.this, R.layout.need_credit, R.id.Exit);
            return;
        }

        UnitSelection.info.setExpiratationMonth(month.getSelectedItem().toString());
        UnitSelection.info.setExpiratationYear(year.getSelectedItem().toString());

        if(checkDate())
        {
            createDialog(CreditCardInfo.this, R.layout.expirationdate, R.id.ExitButton);
            return;
        }

        if(!UnitSelection.info.checkEmptyCreditNumber() && checkCreditCardNumber(credit.getText().toString()))
        {
            Intent confirmPage = new Intent(this, Confirmation.class);
            confirmPage.putExtra(NAME_ON_CARD, name.getText().toString());
            startActivity(confirmPage);
        }
        else
            createDialog(CreditCardInfo.this, R.layout.invalidcreditcardnumber, R.id.ExitButton);
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

    private void showCustomerNotice()
    {

        AlertDialog.Builder notice = new AlertDialog.Builder(CreditCardInfo.this);
        View warning = getLayoutInflater().inflate(R.layout.explainationforcharge, null);
        Button accept = (Button) warning.findViewById(R.id.Accept), decline = (Button) warning.findViewById(R.id.Decline);

        notice.setView(warning);
        final AlertDialog reason = notice.create();
        reason.show();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reason.dismiss();
                alreadyDisplayed = true;
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToBeginning = new Intent(CreditCardInfo.this, Choice.class);
                startActivity(returnToBeginning);
            }
        });

    }

    private boolean checkDate()
    {
        boolean expired = false;

        java.util.Calendar currrent = java.util.Calendar.getInstance();
        int year = Helper.convertStrToNum(UnitSelection.info.getExpiratationYear()),
                month = numberMonth(UnitSelection.info.getExpiratationMonth());

        if(currrent.get(Calendar.YEAR) >= year && (currrent.get(Calendar.MONTH) + 1) >= month)
            expired = true;
        return expired;
    }

    private int numberMonth(String mon) {
        int month = 0;
        switch (mon) {
            case "January":
                month = 1;
                break;

            case "Feburary":
                month = 2;
                break;

            case "March":
                month = 3;
                break;

            case "April":
                month = 4;
                break;

            case "May":
                month = 5;
                break;

            case "June":
                month = 6;
                break;

            case "July":
                month = 7;
                break;

            case "August":
                month = 8;
                break;

            case "September":
                month = 9;
                break;

            case "October":
                month = 10;
                break;

            case "November":
                month = 11;
                break;

            case "December":
                month = 12;
                break;
        }
        return month;

    }


}
