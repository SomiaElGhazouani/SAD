package Practica_1.WithoutMVC;

import Practica_1.resources.Key;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class EditableBufferedReader extends BufferedReader {

    public EditableBufferedReader(Reader in) {
        super(in);
    }

    /**
     * Assigna la consola a mode Raw
     */
    public void setRaw() throws IOException {
        // Llista de comandes a executar a través del ProcessBuilder
        List<String> commandList = new ArrayList<String>();
        commandList.add("/bin/sh");
        commandList.add("-c");
        commandList.add("stty -echo raw </dev/tty");
        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        try {
            // Iniciem el process d'execusio de comandes
            System.out.println("Assignem la consola a mode Raw");
            processBuilder.start();
        } catch (IOException e) {
            System.out.println("Error during setRaw process: " + e);
        }
    }

    /**
     * Assigna la consola a mode Cooked (mode per defecte)
     */
    public void unSetRaw() {
        //Llista de comandes a executar atraves del ProcessBuilder
        List<String> commandList = new ArrayList<String>();
        commandList.add("/bin/sh");
        commandList.add("-c");
        commandList.add("stty echo cooked </dev/tty");
        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        try {
            // Iniciem el process i esperem fins que acabi
            System.out.println("Assignem la consola a mode per defecte (cooked)");
            processBuilder.start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error during unSetRaw process: " + e);
        }
    }

    /**
     * @return La tecla premuda (en int) y a processar
     * @throws IOException
     */
    @Override
    public int read() throws IOException {
        int inputChar = 0;

        // Tractament de tecles especials (ESC)
        inputChar = super.read();

        if (inputChar == Key.ESC) {
            inputChar = super.read();
            if (inputChar == Key.CORXET) {
                inputChar = super.read();
                switch (inputChar) {
                    case Key.in_DRETA:
                        return Key.DRETA;
                    case Key.in_ESQUERRA:
                        return Key.ESQUERRA;
                    case Key.in_INICI:
                        return Key.INICI;
                    case Key.in_FI:
                        return Key.FI;
                    case Key.in_INSERT:
                        if (super.read() == Key.GUIO) { // Eliminar el guió ~
                            return Key.INSERT;
                        }
                        return -1;
                    case Key.in_SUPR:
                        if (super.read() == Key.GUIO) { // Eliminar el guió ~
                            return Key.SUPR;
                        }
                        return -1;
                    default:
                        return -1;
                }
            }
            return -1;
        }
        // Carácter per defecte
        return inputChar;
    }

    /**
     * @return Retorna el valor final escrit per consola
     * @throws IOException
     */
    @Override
    public String readLine() throws IOException {

        // Modifiquem el mode de consola a Raw
        this.setRaw();
        Line line = new Line();
        int input = this.read();

        while (input != Key.ENTER) {
            switch (input) {
                case Key.ESQUERRA:
                    //"Moving to the left"
                    line.moveToLeft();
                    break;
                case Key.DRETA:
                    //"Moving to the right"
                    line.moveToRight();
                    break;
                case Key.INICI:
                    //"Moving to the start"
                    line.moveToStart();
                    break;
                case Key.FI:
                    //"Moving to the end"
                    line.moveToEnd();
                    break;
                case Key.INSERT:
                    //"Switching Insert";
                    line.switchInsert();
                    break;
                case Key.SUPR:
                    //"Deleting last char";
                    line.supr();
                    break;
                case Key.BPSK:
                    line.backspace();
                    break;
                default:
                    line.add((char) input);
                    System.out.print((char) input);
                    break;
            }
            input = this.read();

        }
        // Tornem a modificar la consola per tornar al mode per defecte (cooked)
        this.unSetRaw();

        return line.getText();

    }

}
