package dataproviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;
    private static ConfigReader configReader;

    private ConfigReader()
    {
        BufferedReader reader;
        String propertyFilePath = System.getProperty("user.dir")+ "//src/main//resources//configuration.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public static ConfigReader getInstance( ) {
        if(configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("base_Url");
        if(baseUrl != null) return baseUrl;
        else throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
    }

    public String getBasePath()
    {
        String basePath = properties.getProperty("base_Path");
        if(basePath != null) return basePath;
        else throw new RuntimeException("basePath not specified in the Configuration.properties file.");
    }

    public String getRegisterUrl()
    {
        String registerUrl = properties.getProperty("registerUrl");
        if(registerUrl != null) return registerUrl;
        else throw new RuntimeException("register url not specified in the Configuration.properties file.");
    }

    public String getLoginUrl()
    {
        String loginUrl = properties.getProperty("loginUrl");
        if(loginUrl != null) return loginUrl;
        else throw new RuntimeException("loginUrl url not specified in the Configuration.properties file.");
    }

    public String getResetUrl()
    {
        String resetUrl = properties.getProperty("resetUrl");
        if(resetUrl != null) return resetUrl;
        else throw new RuntimeException("resetUrl url not specified in the Configuration.properties file.");
    }
}
