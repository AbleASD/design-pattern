package factory.abs;

import factory.Reciver;
import factory.Sender;
import factory.WiFiReciver;
import factory.WiFiSender;

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
