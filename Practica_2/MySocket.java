package Practica_2;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MySocket extends Socket {

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public MySocket(Socket socket) throws IOException {
        super();
        //super("server", 8080);
        //System.out.println("fora del try MySocket server");
        try {
            super.connect(new InetSocketAddress(socket.getInetAddress(), socket.getLocalPort()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public MySocket(String host, int port) {
        super();
        try {
            super.connect(new InetSocketAddress(host, port));
            this.bufferedReader = new BufferedReader(new InputStreamReader(super.getInputStream()));
            this.printWriter = new PrintWriter(new OutputStreamWriter(super.getOutputStream()), true);
        } catch (IOException exception) {
            exception.printStackTrace();
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
