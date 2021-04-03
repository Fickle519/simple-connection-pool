package com.exh.simulateconnpool.config;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * this class used to load the connpool.properties Configuration file.
 */
public class ConfigClass {

    private static Properties configMappingProperties;
    // configuration buffered map
    private static Map<String, String> configMap;
    static {
        configMappingProperties = new Properties();
        configMap = new HashMap<String, String>();
        try {
            configMappingProperties.load(ConfigClass.class.getClassLoader().getResourceAsStream("connpool.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration en = configMappingProperties.propertyNames();
        while (en.hasMoreElements()){
            String name = (String) en.nextElement();
            String value = configMappingProperties.getProperty(name);
            configMap.put(name,value);
        }
    }

    public static String getConnConfigValue(String name){
        return configMap.get(name);
    }

}
