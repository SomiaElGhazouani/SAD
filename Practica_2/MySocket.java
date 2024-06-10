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
        System.out.println("fora del try MySocket");
        try {
            System.out.println("Dins del try MySocket");
            super.connect(new InetSocketAddress(socket.getInetAddress(), socket.getLocalPort()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.getInputStream()));
            this.printWriter = new PrintWriter(new OutputStreamWriter(this.getOutputStream()), true);
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
