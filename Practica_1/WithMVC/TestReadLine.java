package Practica_1.WithMVC;

import Practica_1.WithMVC.EditableBufferedReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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