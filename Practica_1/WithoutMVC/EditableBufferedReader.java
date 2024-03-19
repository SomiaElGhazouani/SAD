package Practica_1.WithoutMVC;

import Practica_1.Key;

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
        // Llista de comandes a executar atraves del ProcessBuilder
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
     * @return
     * @throws IOException Keys
     *                     Home: ESC O H, ESC [ 1 ~
     *                     End: ESC O F, ESC [ 4 ~
     *                     Insert: ESC [ 2 ~
     *                     Delete: ESC [ 3 ~
     *                     Right: ESC [ C
     *                     Left: ESC [ D
     */
    @Override
    public int read() throws IOException {
        int lectura = 0;

        lectura = super.read();
        // Tractament de tecles especials (ESC)
        if (lectura == Key.ESC) {
            lectura = super.read();
            if (lectura == Key.CORXET) {
                lectura = super.read();
                switch (lectura) {
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
        return lectura;
    }

    /**
     * @return TODO: Retorna el valor final escrit per consola
     * @throws IOException
     */
    @Override
    public String readLine() throws IOException {
        // Modifiquem el mode de consola a Raw
        this.setRaw();

        Line line = new Line();

        int input = this.read();

        while (input != 13) {
            switch (input) {
                case Key.ESQUERRA:
                    //System.out.print("\033[D");
                    System.out.println("Moving to the left");
                    line.moveToLeft();
                    break;
                case Key.DRETA:
                    //System.out.print("\033[C");
                    System.out.println("Moving to the right");
                    line.moveToRight();
                    break;
                case Key.INICI:
                    System.out.println("Moving to the start");
                    line.moveToStart();
                    break;
                case Key.FI:
                    System.out.println("Moving to the end");
                    line.moveToEnd();
                    break;
                case Key.INSERT:
                    if (line.getInsertMode()) {
                        line.switchInsert();
                        System.out.println("Insert mode OFF");
                    } else {
                        line.switchInsert();
                        System.out.println("Insert mode ON");
                    }
                    break;
                case Key.SUPR:
                    System.out.println("Deleting last char");
                    line.supr();
                    break;
                default:
                    line.add((char) input);
                    System.out.print((char) input);
                    break;
            }
            input = this.read();

        }
        // Tornem la consola al mode per defecte (cooked)
        this.unSetRaw();

        return line.getText();

    }

}
