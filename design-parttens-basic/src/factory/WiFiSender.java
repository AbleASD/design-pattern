package factory;

public class WiFiSender implements Sender {

    @Override
    public void sendDate(byte[] date) {
        System.out.println(String.format("Send %s by Wi-Fi!", date));
    }
    
}
