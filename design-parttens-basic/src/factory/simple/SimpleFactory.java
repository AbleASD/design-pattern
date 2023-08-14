package factory.simple;

import factory.BluetoothSender;
import factory.Sender;
import factory.WiFiSender;
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
