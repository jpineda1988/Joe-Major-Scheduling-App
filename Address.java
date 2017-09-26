package com.joemajorheatingandair.joemajorscheduler;

/**
 * Created by jpineda on 11/27/16.
 */

public class Address {
    int streetNumber, zipCode;
    String streetName, City, State;
    private final String stateNames[] = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
                                 "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                                 "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
                                 "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
                                 "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma",
                                  "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
                                  "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};

    Address(int street, String name, String location, String st, int zip)
    {
        if(street >= 0 && street <= 99999)
            streetNumber = street;
        if(checkStreetName(name))
            streetName = name;
        if(checkCityName(location))
            City = location;
        if(checkState(st))
            State = st;
        if(zip >= 10000 && zip <= 99999)
           zipCode = zip;
    }

    Address()
    {
        streetNumber = zipCode = 0;
        streetName = City = State = "";
    }


    public int getStreetNumber() {
        return streetNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setCity(String city) {
       if(!city.isEmpty() && checkCityName(city));
          City = city;
    }

    public void setState(String state) {
        if(checkState(state))
         State = state;
    }

    public void setStreetName(String streetName) {
        if(checkStreetName(streetName))
        this.streetName = streetName;
    }

    public void setStreetNumber(int streetNumber) {
        if(streetNumber >= 0 && streetNumber <= 99999)
        this.streetNumber = streetNumber;
    }

    public void setZipCode(int zipCode) {
        if(zipCode >= 10000 && zipCode <= 99999)
        this.zipCode = zipCode;
    }

    public String toString()
    {
        return streetNumber + " " + streetName + "\n" + City + ", " + State + " " + zipCode;
    }

    private boolean checkStreetName(String test)
    {
        boolean isName = false;
        for(int index = 0; index < test.length(); index++)
        {
            if(!Helper.isAlpha(test.charAt(index)) || test.charAt(index) != ' ' || test.charAt(index) != '.')
                isName = false;
            isName = true;
        }
        return isName;
    }

    private boolean checkCityName(String test)
    {
        boolean isName = false;
        for(int index = 0; index < test.length(); index++)
        {
            if(!Helper.isAlpha(test.charAt(index)) || test.charAt(index) != ' ')
                isName = false;
            isName = true;
        }
        return isName;
    }

    private boolean checkState(String test)
    {
        boolean isState = false;
        for(int index = 0; index < stateNames.length; index++)
        {
            if(stateNames[index].equals(test))
            {
                isState = true;
                break;
            }
        }
        return isState;
    }
}
