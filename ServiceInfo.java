package com.joemajorheatingandair.joemajorscheduler;

/**
 * Created by jpineda on 5/28/17.
 */

public class ServiceInfo {

     private final String[] SERVICETYPE = {"Installation", "Replacement", "Repair and Maintenance"},
                            TYPEOFUNIT = {"Internal", "Outer", "Split duct", "Themostat", "Ceiling Cassette", "Package Unit",
                                            "Rooftop Air Conditioner", "Tower Air Conditioner", "Other"};
     private String service, unitType;

     public ServiceInfo()
     {
         service = unitType = null;
     }

     public boolean isEmpty()
     {
         return service == null && unitType == null;
     }

     public String getService()
     {
         return service;
     }

     public String getUnitType()
     {
         return unitType;
     }

     public void setService(String type)
     {
         if(checkService(type))
             service = type;
     }

     public void setTypeOfUnit(String type)
     {
       if(checkUnit(type))
             unitType = type;
     }

     private boolean checkService(String check)
     {
         boolean valid = false;
         for(int index = 0; index < SERVICETYPE.length; index++)
         {
             if(check.equals(SERVICETYPE[index]))
             {
                 valid = true;
                 break;
             }
             valid = false;
         }
         return valid;
     }

     private boolean checkUnit(String check)
     {
         boolean valid = false;
         for(int index = 0; index < TYPEOFUNIT.length; index++)
         {
             if(check.equals(TYPEOFUNIT[index]))
             {
                 valid = true;
                 break;
             }
             valid = false;
         }
         return valid;
     }
}
