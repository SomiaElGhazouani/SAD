package Practica_3.src.View;

import javax.swing.*;
import java.awt.*;

public class VistaChat extends JFrame {

    private JList<String> messageList;
    private JTextField messageField;
    private JButton sendButton;

    public VistaChat() {
        setTitle("Chat");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Llista de missatges
        messageList = new JList<>();
        JScrollPane messageScrollPane = new JScrollPane(messageList);
        add(messageScrollPane, BorderLayout.CENTER);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Enviar");
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public JList<String> getMessageList() {
        return messageList;
    }

    public JTextField getMessageField() {
        return messageField;
    }

    public JButton getSendButton() {
        return sendButton;
    }
}