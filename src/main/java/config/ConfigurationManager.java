package config;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConfigurationManager {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigurationManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Error: config.properties file not found!");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}


//Cannot return from within an initializer
//String autoItScriptPath = ConfigurationManager.getProperty("autoit.script.path");
//String fileUploadPath = ConfigurationManager.getProperty("file.upload.path");
//String applicationUrl = ConfigurationManager.getProperty("application.url");
