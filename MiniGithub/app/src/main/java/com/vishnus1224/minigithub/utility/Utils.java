package com.vishnus1224.minigithub.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vishnu on 2/13/2016.
 */
public final class Utils {

    public static final SimpleDateFormat githubDateFormat;

    public static final SimpleDateFormat appDateFormat;

    static {

        githubDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        appDateFormat = new SimpleDateFormat("MMMM, d, yyyy");

    }

    private Utils(){
        
    }

    //key for parceling repository between activities.
    public static final String KEY_REPOSITORY = "repository";

    //Formats the input dateString in the format specified by the output date format.
    public static String formatDate(String dateString, SimpleDateFormat inputDateFormat, SimpleDateFormat outputDateFormat){

        try {

            Date date = inputDateFormat.parse(dateString);

            return outputDateFormat.format(date);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static boolean areStringsEqual(String firstString, String secondString){

        return firstString.equals(secondString);

    }

}
