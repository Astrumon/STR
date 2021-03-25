package com.course_project.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Класс который содержит логику проверки входный данных от пользователя
 */
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
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean checkValidNumber(String number) {
        Pattern pattern = Pattern.compile("^\\+38\\d{10}$");
        Matcher matcher = pattern.matcher(number);


        return matcher.matches();
    }

}
