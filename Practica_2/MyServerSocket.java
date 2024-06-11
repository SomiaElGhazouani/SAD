package Practica_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket extends ServerSocket {

    public MyServerSocket(int port) throws IOException {
        super(port);
    }

    @Override
    public MySocket accept() {
        try {
            //System.out.println("accept2");
            Socket socket = super.accept();
            MySocket socket1 = new MySocket(socket);
            //System.out.println("accept3");
            return socket1;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
