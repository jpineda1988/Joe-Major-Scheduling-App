package com.joemajorheatingandair.joemajorscheduler;

/**
 * Created by jpineda on 11/17/16.
 */


public class AppointmentInfo {
    private int hour, minutes, month, day, year;
    private String name, phoneNum, email, timeOfDay, expiratationMonth, expiratationYear;
    private Address location;
    private ServiceInfo notes;
    private char creditNumber[], securityCode[];


    public AppointmentInfo(int h, int m)
    {
        if(h >= 1 && h <= 12)
            hour = h;
        if(m >= 0 && m <= 59)
            minutes = m;

        location = new Address();
        creditNumber = new char[16];
        securityCode = new char[3];
        name = phoneNum = email = timeOfDay = expiratationMonth = expiratationYear = new String();
    }

    public AppointmentInfo() {
        hour = 1;
        minutes = 0;
        month = 1;
        day = 1;
        year = 1900;
        name = phoneNum = email = timeOfDay = expiratationMonth = expiratationYear = new String();
        location = new Address();
        notes = new ServiceInfo();
        creditNumber = new char[16];
        securityCode = new char[3];
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getYear()
    {
        return year;
    }

    public String getAMorPM()
    {
        return timeOfDay;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail()
    {
        return email;
    }

    public int getStreetNumber()
    {
        return location.getStreetNumber();
    }

    public String getStreetName()
    {
        return location.getStreetName();
    }

    public String getCity()
    {
        return location.getCity();
    }

    public String getState()
    {
        return location.getState();
    }

    public int getZipCode()
    {
        return location.getZipCode();
    }

    public ServiceInfo getServiceInfo()
    {
        return notes;
    }

    public char[] getCreditNumber()
    {
        return creditNumber;
    }

    public char[] getSecurityCode()
    {
        return securityCode;
    }

    public String getExpiratationMonth()
    {
        return expiratationMonth;
    }

    public String getExpiratationYear()
    {
        return expiratationYear;
    }

    public void setHour(int time)
    {
        if(time >= 1 && time <= 12)
            hour = time;
    }

    public void setMinutes(int time)
    {
        if(time >= 0 && time <= 59)
            minutes = time;
    }

    public void setMonth(int m)
    {
        if(m >= 1 &&  m <= 12)
          month = m;
    }

    public void setDay(int d)
    {
        day = d;
    }

    public void setYear(int y)
    {
        year = y;
    }

    public void setTimeOfDay(String time)
    {
        if(time == "AM" || time == "PM")
            timeOfDay = time;
    }

    public void setName(String person)
    {
        for(int index = 0; index < person.length(); index++) {
           if(!Helper.isAlpha(person.charAt(index)) && person.charAt(index) != ' ')
               return;
        }
        name = person;
    }

    public void setPhoneNum(String phone)
    {
        for(int index = 0; index < phone.length(); index++)
        {
            if(!(phone.charAt(index) >= '0' && phone.charAt(index) <= '9') && phone.charAt(index) != '-')
              return;
        }
        phoneNum = phone;
    }

    public void setStreetNumber(int num)
    {
        location.setStreetNumber(num);
    }

    public void setStreetName(String name)
    {
        location.setStreetName(name);
    }

    public void setCity(String city)
    {
        location.setCity(city);
    }

    public void setState(String state)
    {
        location.setState(state);
    }

    public void setZipCode(int zip)
    {
        location.setZipCode(zip);
    }

    public void setService(ServiceInfo s)
    {
        notes = s;
    }

    public void setEmail(String mail)
    {
        if(checkEmail(mail))
            email = mail;
    }

    public void setCreditNumber(String number)
    {
        for(int index = 0; !number.isEmpty() && index < number.length(); index++)
            if(Helper.isNumeric(number.charAt(index)))
                creditNumber[index] = number.charAt(index);
    }

    public void setSecurityCode(String number)
    {
        for(int index = 0; !number.isEmpty() && index < 3; index++)
            if(Helper.isNumeric(number.charAt(index)))
                securityCode[index] = number.charAt(index);
    }

    public void setExpiratationMonth(String month)
    {
        expiratationMonth = month;
    }

    public void setExpiratationYear(String year)
    {
        expiratationYear = year;
    }

    public boolean checkEmptyCreditNumber()
    {
        boolean isEmpty = false;

        for (int index = 0; index < creditNumber.length; index++)
        {
            if (creditNumber[index] != 0) {
                isEmpty = false;
                break;
            }
            isEmpty = true;
        }
        return isEmpty;
    }

    private boolean checkEmail(String check)
    {
        boolean valid = false;
        for(int index = 0; index < check.length(); index++)
        {
            if(!Helper.isNumeric(check.charAt(index)) && !Helper.isAlpha(check.charAt(index)) && check.charAt(index) != '@' && check.charAt(index) != '.' && check.charAt(index) != '_')
            {
                valid = false;
                break;
            }
            valid = true;
        }
        return valid;
    }
}
