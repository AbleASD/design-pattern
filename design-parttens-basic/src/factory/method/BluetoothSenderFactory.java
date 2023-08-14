package factory.method;

import factory.BluetoothSender;
import factory.Sender;

public class BluetoothSenderFactory implements SenderFactory {

    @Override
    public Sender createSender() {
        return new BluetoothSender();
    }
    
}
