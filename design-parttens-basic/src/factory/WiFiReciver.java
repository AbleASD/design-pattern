package factory;

public class WiFiReciver implements Reciver{

    @Override
    public void reciverDate(byte[] date) {
        System.out.println(String.format("WiFi reciver recived date: %s", date));
    }
    
}
