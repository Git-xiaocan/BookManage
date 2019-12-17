package com.xiaocan.bookmanage.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 封装了对配置文件的操作
 */
public class Configurations {
    private static Properties[] properties = null;

    static {
        String[] propertiesFile = {
                "DAOConfig.properties",
                "DataBaseConfig.properties",
                "TableConfig.properties",
                "entityConfig.properties",
                "ServiceConfig.properties"
        };
        properties = new Properties[propertiesFile.length];
        for (int i = 0; i < properties.length; i++) {
            properties[i] = new Properties();
            try {
                properties[i].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config//" + propertiesFile[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static String get(String key) {
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getProperty(key) != null) {
                return properties[i].getProperty(key);
            }
        }
        return null;
    }
}
