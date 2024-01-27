package cc.wordview.ytm;

import java.io.InputStream;
import java.util.Properties;

public class ApiKeyLoader {
    public static String getApiKey() {
        try (InputStream input = ApiKeyLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Unable to find config.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("api.key");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}