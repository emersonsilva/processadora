package br.com.processadora.socketserver.util;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {

    private final static String SOCKET_PROPERTIES = "./properties/socket.properties";

    public static String getProp(String key) {
        return returnProperties().getProperty(key);
    }

    private static Properties returnProperties() {
        Properties props = new Properties();
        FileInputStream file = null;
        try {
            file = new FileInputStream(SOCKET_PROPERTIES);
            props.load(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }

}
