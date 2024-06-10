package Practica_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket extends ServerSocket {

    public MyServerSocket(int port) throws IOException {
        super(port);
    }

    public MySocket accept2(){
        try{
            System.out.println("accept2");
            Socket socket = this.accept();
            return new MySocket(socket);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
