package factory.abs;

import factory.BluetoothReciver;
import factory.BluetoothSender;
import factory.Reciver;
import factory.Sender;

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
