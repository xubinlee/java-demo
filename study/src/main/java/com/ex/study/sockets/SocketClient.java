package com.ex.study.sockets;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8765);
        OutputStream outputStream = socket.getOutputStream();
        String message="hello";
        byte[] bytes = message.getBytes("UTF-8");
        outputStream.write(bytes.length>>8);
        outputStream.write(bytes.length);
        outputStream.write(bytes);
        outputStream.flush();
        String msg="hello again";
        byte[] msgBytes = msg.getBytes("UTF-8");
        outputStream.write(msgBytes.length>>8);
        outputStream.write(msgBytes.length);
        outputStream.write(msgBytes);
        outputStream.close();
        socket.close();
    }
}
