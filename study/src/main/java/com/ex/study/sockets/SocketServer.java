package com.ex.study.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8765);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        while (true){
            int first = inputStream.read();
            if (first==-1) {
                break;
            }
            int second = inputStream.read();
            int length = (first << 8) + second;
            byte[] bytes = new byte[length];
            inputStream.read(bytes);
            System.out.println(new String(bytes, "UTF-8"));
        }
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
