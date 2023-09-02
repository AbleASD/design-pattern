package com.able.factory.abs;

import com.able.factory.BluetoothReciver;
import com.able.factory.BluetoothSender;
import com.able.factory.Reciver;
import com.able.factory.Sender;

public class BluetoothFactory extends AbstractFactory {

    @Override
    public Sender createSender() {
        return new BluetoothSender();
    }

    @Override
    public Reciver createReciver() {
        return new BluetoothReciver();
    }
    
}
