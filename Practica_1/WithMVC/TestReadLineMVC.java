package Practica_1.WithMVC;

import Practica_1.WithMVC.Controller.EditableBufferedReader;
import Practica_1.WithMVC.View.VistaConsola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TestReadLineMVC {
    public static void main(String[] args) {
        VistaConsola vistaConsola = new VistaConsola();

        BufferedReader editableBufferedReader = new EditableBufferedReader(
                new InputStreamReader(System.in), vistaConsola);
        String finalText = null;
        try {
            finalText = editableBufferedReader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        vistaConsola.println("\nline is: " + finalText);
    }
}