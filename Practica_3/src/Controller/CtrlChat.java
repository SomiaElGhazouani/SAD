package Practica_3.src.Controller;

import Practica_3.src.resources.MySocket;
import Practica_3.src.View.VistaChat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlChat {

    private VistaChat view;
    private MySocket socket;

    public CtrlChat(VistaChat view, MySocket socket) {

        this.view = view;
        this.socket = socket;

        // Creació de model per guardar els missatges en comptes de crear una classe Model Missatges
        DefaultListModel<String> messageListModel = new DefaultListModel<>();
        view.getMessageList().setModel(messageListModel);

        this.view.getSendButton().addActionListener(new SendButtonListener());
        this.view.getMessageField().addActionListener(new SendButtonListener());

        // Escoltant missatges del servidor
        new Thread(() -> {
            String inputText;
            while ((inputText = socket.readLine()) != null) {
                System.out.println(inputText);
            }
        }).start();
    }

    class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputText = view.getMessageField().getText();
            if (!inputText.isEmpty()) {
                ((DefaultListModel<String>) view.getMessageList().getModel()).addElement("Jo: " + inputText);
                socket.println(inputText);
                System.out.println("Jo: "  + inputText);
                view.getMessageField().setText("");
            }
        }
    }

}
