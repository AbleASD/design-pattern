package com.able.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TcpSocketClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        TcpSocketClient tcpSocketClient = new TcpSocketClient();
        tcpSocketClient.client();
    }

    public void client() throws UnknownHostException, IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("等待连接！");
            try (Socket socket = new Socket("127.0.0.1", 9528)) {
                System.out.println("连接成功");

                while (true) {
                    System.out.print("客户端: ");
                    String s = scanner.next();
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(s.getBytes(StandardCharsets.UTF_8));
                    if ("out".equals(s)) {
                        break;
                    }
                    byte[] bytes = new byte[1024];

                    InputStream inputStream = socket.getInputStream();

                    int read = inputStream.read(bytes);

                    System.out.println("服务端: " + new String(bytes, 0, read, Charset.forName("UTF-8")));
                }
            }
        }
    }
}
