package Practica_3.src.resources;

import java.io.IOException;
import java.net.ServerSocket;

public class MyServerSocket {

    private final ServerSocket serverSocket;

    public MyServerSocket(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public MySocket accept() {
        try {
            return new MySocket(this.serverSocket.accept());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
