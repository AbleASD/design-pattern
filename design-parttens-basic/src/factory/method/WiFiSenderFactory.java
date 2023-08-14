package factory.method;

import factory.Sender;
import factory.WiFiSender;

public class WiFiSenderFactory implements SenderFactory {

    @Override
    public Sender createSender() {
        return new WiFiSender();
    }
    
}
