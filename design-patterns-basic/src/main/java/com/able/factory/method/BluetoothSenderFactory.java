package com.able.factory.method;

import com.able.factory.BluetoothSender;
import com.able.factory.Sender;

public class BluetoothSenderFactory implements SenderFactory {

    @Override
    public Sender createSender() {
        return new BluetoothSender();
    }
    
}
