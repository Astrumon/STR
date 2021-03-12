package com.course_project.data_access;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class DataSource {
    public final static String PATH = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course_project\\src\\main\\java\\com\\course_project\\database\\railway.db";
    private String url;


    public DataSource() {
        url = PATH;
    }

    public DataSource(String url) {
        this.url = url;
    }
    public Connection getConnection() throws SQLException {

        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
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
