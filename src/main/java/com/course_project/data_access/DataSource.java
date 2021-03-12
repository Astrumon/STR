package com.course_project.data_access;



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
    public final static String PATH = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course_project\\src\\main\\java\\com\\course_project\\database\\railway.db";

    private String url;
    private SQLiteConfig config;

    public String readPathFromConfiguration() {

        String path = "jdbc:sqlite:";
        File file;
        try {
            file = new File("configuration.txt");
            file.createNewFile();

            try(FileReader fileReader =  new FileReader(file.getName())) {
                int c = 0;
                while((c = fileReader.read()) != -1) {
                    path += (char)c;
                }
            } catch (IOException exc) {
                System.out.println(exc);
            }
        } catch (IOException exc) {
            System.out.println(exc);
        }


        return path;
    }

    public void setConfig() {
        config = new SQLiteConfig();
        config.setOpenMode(SQLiteOpenMode.READWRITE);
        config.enforceForeignKeys(true);
        config.setReadOnly(false);

    }

    public DataSource() {
        System.out.println(readPathFromConfiguration());
        url = readPathFromConfiguration();
        config = new SQLiteConfig();
        config.setOpenMode(SQLiteOpenMode.READWRITE);
        config.enforceForeignKeys(true);
        config.setReadOnly(false);
        System.out.println("testConfig" + config.toProperties());
    }

    public DataSource(String url) {
        this.url = url;
    }
    public Connection getConnection() throws SQLException {


//        Properties cnfg = new Properties();
//        cnfg.setProperty("open_mode","0");
//        cnfg.setProperty("PRAGMA foreign_keys", "ON");



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
