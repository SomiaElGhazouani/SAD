package Practica_2;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, MySocket> clientMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        int auxPort = 8080;
        MyServerSocket myServerSocket = new MyServerSocket(auxPort);
        System.out.println("MyServerSocket creat correctament");

        while (true) {
            MySocket mySocket = myServerSocket.accept();
            mySocket.println("Conectat i provant el println");

            new Thread(() -> {
                mySocket.println("Intrudueix el vostre nom d'usuari: ");
                String userName = mySocket.readLine();
                clientMap.put(userName, mySocket);
                System.out.println(userName + " ha entrat al xat");
                mySocket.println("Benvingut al xat " + userName);
                String inputText;
                while ((inputText = mySocket.readLine()) != null) {
                    broadcast(inputText, userName);
                    System.out.println(userName + " ha dit: " + inputText);

                }
                System.out.println(userName + " ha abandonat el xat");
                clientMap.remove(userName);
                mySocket.close();
            }).start();
        }
    }

    public static void broadcast(String inputText, String userName) {

        for (Map.Entry<String, MySocket> entry : clientMap.entrySet()) {
            if (!userName.equals(entry.getKey())) {
                entry.getValue().println(userName + ": " + inputText);
            }
        }

    }
}

