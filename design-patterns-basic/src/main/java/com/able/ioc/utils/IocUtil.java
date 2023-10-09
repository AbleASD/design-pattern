package com.able.ioc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IocUtil {
    public static Properties getPropertyByName(String fileName) {
        InputStream is = null;
        Properties properties = null;

        try {
            is= IocUtil.class.getClassLoader().getResourceAsStream(fileName);
            properties = new Properties();
            properties.load(is);
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    public static String toLowercaseIndex(String name) {
        if (!name.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            sb.append(name.substring(0, 1).toLowerCase());
            sb.append(name.substring(1, name.length()));
            return sb.toString();
        }
        return null;
    }
}
