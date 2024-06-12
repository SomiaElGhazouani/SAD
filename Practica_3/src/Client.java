package Practica_3.src;

import Practica_3.resources.Properties;
import Practica_3.src.Controller.CtrlChat;
import Practica_3.src.resources.MySocket;
import Practica_3.src.View.VistaChat;

import javax.swing.*;
import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        try {
            //Conectar al servidor
            MySocket mySocket = new MySocket(Properties.localhost, Properties.port);

            VistaChat view = new VistaChat();
            CtrlChat controller = new CtrlChat(view, mySocket);

            // Solicitar el nombre de usuario
            String userName = JOptionPane.showInputDialog(null, "Introdueix el vostre nom d'usuari:", "Nom d'usuari", JOptionPane.PLAIN_MESSAGE);

            if (userName == null || userName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nom d'usuari no pot estar buit", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            mySocket.println(userName);
            view.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
