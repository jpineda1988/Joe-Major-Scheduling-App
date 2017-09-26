package com.joemajorheatingandair.joemajorscheduler;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by jpineda on 7/3/17.
 */

public class Helper {

    public static final int NOT_VALID = -1;

    public static void hidekeyboard(View current, Activity activity)
    {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if(current == null)
            current = new View(activity);
        imm.hideSoftInputFromWindow(current.getWindowToken(), 0);
    }

    public static int convertStrToNum(String target)
    {
        int number = 0, multiply = 1;
        for(int index = target.length() - 1; index >= 0; index--)
        {
            number += charToNum(target.charAt(index))*multiply;
            multiply *= 10;
        }
        return number;
    }

    public static int charToNum(char character)
    {
        if(isNumeric(character))
            return character - '0';
        else
            return NOT_VALID;
    }

    public static boolean isNumeric(char character)
    {
        return (character >= '0') && (character <= '9');
    }

    public static boolean isAlpha(char character)
    {
        return (character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z');
    }
}
