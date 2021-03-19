package com.course_project.database;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteOpenMode;

public class DataSource {
    public final static String PATH = "jdbc:sqlite::resource:rail_way.db";

    private String url;
    private SQLiteConfig config;


    public void setConfig() {
        config = new SQLiteConfig();
        config.setOpenMode(SQLiteOpenMode.READWRITE);
        config.enforceForeignKeys(true);
        config.setReadOnly(false);
    }

    public DataSource() {

        url = PATH;
        url = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course_project\\src\\main\\resources\\railway.db";
        //url = "jdbc:sqlite:" + getClass().getResource("/resources/railway.db");

        config = new SQLiteConfig();
        config.setOpenMode(SQLiteOpenMode.READWRITE);
        config.enforceForeignKeys(true);
        config.setReadOnly(false);
    }

    public DataSource(String url) {
        this.url = url;
    }
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, config.toProperties());
    }

    public void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
