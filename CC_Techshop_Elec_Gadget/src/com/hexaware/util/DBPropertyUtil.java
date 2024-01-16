package com.hexaware.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
	private static final String PROPERTY_FILE = "db.properties";
    public static String getConnectionString() {
    	Properties properties = new Properties();
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
            properties.load(input);
            return "jdbc:mysql://" +
                    properties.getProperty("hostname") +
                    ":" + properties.getProperty("port") +
                    "/" + properties.getProperty("dbname") +
                    "?user=" + properties.getProperty("username") +
//                    "&password=" + properties.getProperty("password");
                    "&password=73524Idea%23";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
