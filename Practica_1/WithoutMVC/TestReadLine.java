package Practica_1.WithoutMVC;

import java.io.*;

class TestReadLine {
    public static void main(String[] args) {
        BufferedReader editableBufferedReader = new EditableBufferedReader(
                new InputStreamReader(System.in));
        String finalText = null;
        try {
            finalText = editableBufferedReader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("\nline is: " + finalText);
    }
}