package com.course_project.support;

/**
 * Класс который служит для парсинга "Вагон№", позволяет выбирать со строки таблицы только номер вагона
 */
public class ParseId {

    private static String text;

    public ParseId(String text) {
        ParseId.text = text;
    }

    public static Long getLongId(String text,String key) {
        return Long.parseLong(text.substring(key.length()));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        ParseId.text = text;
    }
}
