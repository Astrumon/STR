package com.course_project.data_access.model;

public class Operator {
    public static final String TABLE_NAME = "operator";
    public static final String ID_COLUMN = "id";
    public static final String LOGIN_COLUMN = "login";
    public static final String PASSWORD_COLUMN = "password";

    private String login, password;
    private Long id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return "Operator[ "
                + "login= " + this.login
                + ", password= " + this.password
                + ", id= " + this.id
                + "]";
    }

    public void setId(Long id) {
        this.id = id;
    }
}
