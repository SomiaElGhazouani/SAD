package Practica_1.WithoutMVC;

import Practica_1.Key;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditableBufferedReader extends BufferedReader {

    public EditableBufferedReader(Reader in) {
        super(in);
    }

    /**
     * Assigna la consola a mode Raw
     */
    public void setRaw() throws IOException {
        // Llista de comandes a executar atraves del ProcessBuilder
        List<String> commandList = Arrays.asList("bin/sh","-c","stty -echo raw </dev/tty");
//        commandList.add("/bin/sh");
//        commandList.add("-c");
//        commandList.add("stty -echo raw </dev/tty");
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
        System.out.println("Inici del unSetRaw");
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
     * @return
     * @throws IOException
     *      Keys
     *          Home: ESC O H, ESC [ 1 ~
     *          End: ESC O F, ESC [ 4 ~
     *          Insert: ESC [ 2 ~
     *          Delete: ESC [ 3 ~
     *          Right: ESC [ C
     *          Left: ESC [ D
     */
    @Override
    public int read() throws IOException {
        int inputKey;
        inputKey = super.read();
        System.out.println("Inici del read: " + inputKey);

        if (inputKey != Key.ESC) return inputKey;
        switch (inputKey = super.read()) {
            case '[':
                switch (inputKey = super.read()) {
                    case 'D':
                        return Key.LEFT;
                    case 'C':
                        return Key.RIGHT;
                    case '1':
                        if (super.read() == '~')
                            return Key.INICIO;
                    case '2':
                        if (super.read() == '~')
                            return Key.INSERT;
                    case '3':
                        if (super.read() == '~')
                            return Key.SUPR;
                    case '4':
                        if (super.read() == '~')
                            return Key.FIN;
                    default:
                        return inputKey;

                }
            case 'O':
                return switch (inputKey = super.read()) {
                    case 'H' -> Key.INICIO;
                    case 'F' -> Key.FIN;
                    default -> inputKey;
                };
            default:
                return inputKey;
        }
    }

    /**
     * @return TODO: Retorna el valor final escrit per consola
     * @throws IOException
     */
    @Override
    public String readLine() throws IOException {
        System.out.println("Inici del readLine");
        // Modifiquem el mode de consola a Raw
        this.setRaw();

        Line line = new Line();

        int input = this.read();
        System.out.println("readline recibe: " + input
        );
        while(input != 13){
            switch (input){
                case Key.LEFT -> {
                    System.out.println("Moving to the left");
                    line.moveToLeft();
                }
                case Key.RIGHT -> {
                    System.out.println("Moving to the right");
                    line.moveToRight();
                }
                case Key.INICIO -> {
                    System.out.println("Moving to the start");
                    line.moveToStart();
                }
                case Key.FIN -> {
                    System.out.println("Moving to the end");
                    line.moveToEnd();
                }
                case Key.INSERT -> {
                    System.out.println("Switch to Insert mode");
                    line.switchInsert();
                }
                case Key.SUPR -> {
                    System.out.println("Deleting last char");
                    line.supr();
                }
                default -> {
                    line.add((char) input);
                    System.out.println("readline imprimo: " + input + " ---- " + (char) input);
                }
            }
            System.out.println("leo otra vez en readline");
            input = this.read();

        }

        // Tornem la consola al mode per defecte (cooked)
        this.unSetRaw();

        return line.getText();

    }

}
