package Practica_2.src;

import Practica_2.resources.Properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws IOException {

        MySocket mySocket = new MySocket(Properties.localhost, Properties.port);

        // Input THread
        new Thread(() -> {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String inputText;
            try {
                while ((inputText = in.readLine()) != null) {
                    mySocket.println(inputText);
                }
                mySocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();

        // Output Thread
        new Thread(() -> {
            String inputText;
            while ((inputText = mySocket.readLine()) != null) {
                System.out.println(inputText);
            }
            System.out.println("Client desconectat");
            mySocket.close();
        }).start();
    }
}
