package com.course_project.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class Checker {

    public static boolean checkPositiveIntValue(String value) {
        if (value.matches("[-]?\\d+")) {
            return checkPositiveValue(Integer.parseInt(value));
        }
        return false;
    }

    public static boolean checkStringValue(String value) {
        return value.matches("[\\p{L}| a-zA-Z0-9]+");
    }

    public static boolean checkPositiveValue(Integer value) {
        return value > 0;
    }

    public static boolean checkEmptyValue(String value) {
        return value.isEmpty();
    }

    public static boolean isValidTime(String curDate) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        timeFormat.setLenient(false);

        Date parsedDate = null;

        try {
            parsedDate = timeFormat.parse(curDate);
        } catch (ParseException e) {
            System.out.println(e);
        }


        return parsedDate != null;
    }

    public static boolean checkValidEmail(String value) {
        return EmailValidator.getInstance().isValid(value);
    }

    public static boolean checkValidNumber(String number) {
//        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
//        Matcher matcher = pattern.matcher(number);

        Pattern pattern = Pattern.compile("^\\+38\\d{10}$");
        Matcher matcher = pattern.matcher(number);


        return matcher.matches();
    }

}
