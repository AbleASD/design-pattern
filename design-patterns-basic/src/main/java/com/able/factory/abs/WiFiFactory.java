package com.able.factory.abs;

import com.able.factory.Reciver;
import com.able.factory.Sender;
import com.able.factory.WiFiReciver;
import com.able.factory.WiFiSender;

public class WiFiFactory extends AbstractFactory {

    @Override
    public Sender createSender() {
        return new WiFiSender();
    }

    @Override
    public Reciver createReciver() {
        return new WiFiReciver();
    }
    
}
