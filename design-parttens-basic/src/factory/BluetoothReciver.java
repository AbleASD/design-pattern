package factory;

public class BluetoothReciver implements Reciver {

    @Override
    public void reciverDate(byte[] date) {
        System.out.println(String.format("Bluetooth reciver recived date: %s", date));
    }
    
}
