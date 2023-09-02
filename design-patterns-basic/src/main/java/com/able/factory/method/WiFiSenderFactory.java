package com.able.factory.method;

import com.able.factory.Sender;
import com.able.factory.WiFiSender;

public class WiFiSenderFactory implements SenderFactory {

    @Override
    public Sender createSender() {
        return new WiFiSender();
    }
    
}
