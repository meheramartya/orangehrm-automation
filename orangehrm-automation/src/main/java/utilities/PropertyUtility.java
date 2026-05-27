package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import constants.FrameworkConstants;

public class PropertyUtility {

    private static Properties properties =
            new Properties();

    static {

        try {

            FileInputStream fis =
                    new FileInputStream(
                            FrameworkConstants.CONFIG_PATH);

            properties.load(fis);

        } catch(IOException e) {

            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}