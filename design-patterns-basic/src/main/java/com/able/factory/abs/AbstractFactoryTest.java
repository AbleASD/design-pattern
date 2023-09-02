package com.able.factory.abs;

import com.able.factory.Reciver;
import com.able.factory.Sender;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory factory = new BluetoothFactory();
        Sender sender = factory.createSender();
        Reciver reciver = factory.createReciver();
        byte[] bytes = new byte[3];
        sender.sendDate(bytes);
        reciver.reciverDate(bytes);
    }
}
