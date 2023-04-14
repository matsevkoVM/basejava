package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private static String credentials;
    private final File storageDir;
    private final Properties dbUrl = new Properties();
    private final Properties dbUser = new Properties();
    private final Properties dbPassword = new Properties();

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream inputStream = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(inputStream);
            storageDir = new File(props.getProperty("storage.dir"));

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    private static String getCredentials(Properties pr, String str) {
        try (FileInputStream fis = new FileInputStream(PROPS)) {
            pr.load(fis);
            credentials = Config.getInstance().getDbUrl().getProperty(str);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return credentials;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Properties getDbUrl() {
        return dbUrl;
    }

    public Properties getDbUser() {
        return dbUser;
    }

    public Properties getDbPassword() {
        return dbPassword;
    }
}
