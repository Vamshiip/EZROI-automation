package utils;

import config.ConfigurationManager;

public class ConfigHelper {
    public static String getAutoItScriptPath() {
        return ConfigurationManager.getProperty("autoit.script.path");
    }

    public static String getFileUploadPath() {
        return ConfigurationManager.getProperty("file.upload.path");
    }

    public static String getApplicationUrl() {
        return ConfigurationManager.getProperty("application.url");
    }
}
