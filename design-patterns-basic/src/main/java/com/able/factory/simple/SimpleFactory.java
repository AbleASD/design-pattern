package com.able.factory.simple;

import com.able.factory.BluetoothSender;
import com.able.factory.Sender;
import com.able.factory.WiFiSender;

/**
 * 简单工厂模式
 */
public class SimpleFactory {
    public static Sender createSender(String mode) {
        switch(mode) {
            case "Wi-Fi":
                return new WiFiSender();
            case "Bluetooth":
                return new BluetoothSender();
            default:
                throw new IllegalArgumentException("illegal type: " + mode);
        }
    }
}
