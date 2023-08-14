/**
 * @Author: AbleASD 
 * @Date: 2023-08-12 14:22:39 
 * @Last Modified by: AbleASD
 * @Last Modified time: 2023-08-12 14:38:22
 */
package factory;

public class BluetoothSender implements Sender {

    @Override
    public void sendDate(byte[] date) {
        System.out.println(String.format("Send %s by Bluetooth!", date));
    }
    
}
