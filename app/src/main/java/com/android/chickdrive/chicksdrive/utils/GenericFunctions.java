package com.android.chickdrive.chicksdrive.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GenericFunctions {

    private static final String TAG = "GenericFunctions";

    public static String ConvertIntoDateTimeFormat(String apiDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM d, yyyy");
        SimpleDateFormat converDateFormat = new SimpleDateFormat("MM/d/yyyy");
        try {
            Date date = simpleDateFormat.parse(apiDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return converDateFormat.format(c.getTime());
        } catch (ParseException e) {
            Log.e(TAG, "ConvertIntoDateTimeFormat: " + e.getMessage());

            return null;
        }

    }

    public static String ConvertApiIntoDateTimeFormat(String apiDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d'T'hh:mm:ss");
        SimpleDateFormat converDateFormat = new SimpleDateFormat("MM/d/yyyy");
        try {
            Date date = simpleDateFormat.parse(apiDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return converDateFormat.format(c.getTime());
        } catch (ParseException e) {
            Log.e(TAG, "ConvertIntoDateTimeFormat: " + e.getMessage());

            return null;
        }

    }

    public static String ConvertApiIntoMonth(String apiDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d'T'hh:mm:ss");
        SimpleDateFormat converDateFormat = new SimpleDateFormat("MM");
        try {
            Date date = simpleDateFormat.parse(apiDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return converDateFormat.format(c.getTime());
        } catch (ParseException e) {
            Log.e(TAG, "ConvertIntoDateTimeFormat: " + e.getMessage());

            return null;
        }

    }

    public static String ConvertApiIntoTime(String apiDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d'T'hh:mm:ss");
        SimpleDateFormat converDateFormat = new SimpleDateFormat("hh:mm a");
        try {
            Date date = simpleDateFormat.parse(apiDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return converDateFormat.format(c.getTime());
        } catch (ParseException e) {
            Log.e(TAG, "ConvertIntoDateTimeFormat: " + e.getMessage());

            return null;
        }

    }

    public static String ConvertApiIntoMonthWord(String apiDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d'T'hh:mm:ss");
        SimpleDateFormat converDateFormat = new SimpleDateFormat("MMMM");
        try {
            Date date = simpleDateFormat.parse(apiDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return converDateFormat.format(c.getTime());
        } catch (ParseException e) {
            Log.e(TAG, "ConvertIntoDateTimeFormat: " + e.getMessage());

            return null;
        }

    }

    public static String ConvertApiIntoMonthWordDayYear(String apiDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d'T'hh:mm:ss");
        SimpleDateFormat converDateFormat = new SimpleDateFormat("MMMM d,yyyy");
        try {
            Date date = simpleDateFormat.parse(apiDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return converDateFormat.format(c.getTime());
        } catch (ParseException e) {
            Log.e(TAG, "ConvertIntoDateTimeFormat: " + e.getMessage());

            return null;
        }

    }

    public static String ConvertApiIntoWordDay(String apiDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d'T'hh:mm:ss");
        SimpleDateFormat converDateFormat = new SimpleDateFormat("EEEE");
        try {
            Date date = simpleDateFormat.parse(apiDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return converDateFormat.format(c.getTime());
        } catch (ParseException e) {
            Log.e(TAG, "ConvertIntoDateTimeFormat: " + e.getMessage());

            return null;
        }

    }
}
