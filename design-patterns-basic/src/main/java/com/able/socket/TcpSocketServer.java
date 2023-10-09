package com.able.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TcpSocketServer {
    
    public static void main(String[] args) throws IOException {
        TcpSocketServer tcpSocketServer = new TcpSocketServer();
        tcpSocketServer.server();
    }
    public void server() throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            try (ServerSocket serverSocket = new ServerSocket(9528)) {
                System.out.println("等待连接！");
                Socket client = serverSocket.accept();
                System.out.println("连接成功");
                while(true) {
                    InputStream inputStream = client.getInputStream();
                    byte[] bytes = new byte[1024];

                    int read = inputStream.read(bytes);

                    System.out.println("客户端: " + new String(bytes, 0, read, Charset.forName("UTF-8")));

                    System.out.print("服务端: ");

                    String next = scanner.next();
                    client.getOutputStream().write(next.getBytes(StandardCharsets.UTF_8));
                    if ("out".equals(next)) {
                        break;
                    }
                }
            }
        }
    }

}
