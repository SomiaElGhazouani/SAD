package Practica_3.src.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MySocket extends Socket {

    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;

    public MySocket(Socket socket) throws IOException {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException exception) {
            throw new IOException("MySocket first constructor failed - ", exception);
        }

    }

    public MySocket(String host, int port) throws IOException {
        try {
            super.connect(new InetSocketAddress(host, port));
            this.bufferedReader = new BufferedReader(new InputStreamReader(super.getInputStream()));
            this.printWriter = new PrintWriter(super.getOutputStream(), true);
        } catch (IOException exception) {
            throw new IOException("MySocket second constructor failed - ", exception);
        }
    }

    public void println(String inputText) {
        printWriter.println(inputText);
    }

    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            bufferedReader.close();
            printWriter.close();
            super.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
