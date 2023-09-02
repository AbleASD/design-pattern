package com.able.factory.simple;

import com.able.factory.Sender;

public class Test {
    private static String mode;  // Wi-Fi | Bluetooth

    public static void onClick() {
        byte[] date = {0x00, 0x01};
        Sender sender = SimpleFactory.createSender(mode);
        sender.sendDate(date);
    }

    public static void main(String[] args) {
        mode = "Wi-Fi";
        onClick();
    }
}
