package com.urise.webapp.utils;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ConnectionFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlHelper {
    private static final File PROPS = new File("config\\resumes.properties");
    private static final Resume RESUME = new Resume();
    private static ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }


    public static String getCredentials(Properties pr, String str) {
        String credentials = "";
        try (FileInputStream fis = new FileInputStream(PROPS)) {
            pr.load(fis);
            credentials = Config.getInstance().getDbUrl().getProperty(str);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return credentials;
    }


}
