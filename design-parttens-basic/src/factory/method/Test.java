package factory.method;

import factory.Sender;

public class Test {
    private static String mode;

    public static void onClick() {
        byte[] date = {0x00, 0x01};
        SenderFactory factory;
        switch(mode)  {
            case "Wi-Fi":
                factory = new WiFiSenderFactory();
                break;
            case "Bluetooth":
                factory = new BluetoothSenderFactory();
                break;
            default:
                throw new IllegalArgumentException("Illegal type: " + mode);
        }
        Sender sender = factory.createSender();
        sender.sendDate(date);
    }

    public static void main(String[] args) {
        mode = "Wi-Fi";
        onClick();
    }
}
