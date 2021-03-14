package com.course_project.support;

public class Checker {

    public static boolean checkPositiveIntValue(String value) {
        if (value.matches("[-]?\\d+")) {
            return checkPositiveValue(Integer.parseInt(value));
        }
        return false;
    }

    public static boolean checkStringValue(String value) {
        return !checkPositiveIntValue(value) && value.matches("[\\p{L}| ]+");
    }

    public static boolean checkPositiveValue(Integer value) {
        return value > 0;
    }

    public static boolean checkEmptyValue(String value) {
        return value.isEmpty();
    }

}
