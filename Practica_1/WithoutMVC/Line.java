package Practica_1.WithoutMVC;

import java.util.Observable;

public class Line extends Observable {

    private StringBuilder inputText;
    private int index;
    private boolean insertMode;

    public boolean getInsertMode() {
        return insertMode;
    }

    public Line() {
        inputText = new StringBuilder(); // Altre opció utilitzar List de chars
        index = 0;
        insertMode = false;
    }

    public void moveToLeft() {
        if (index > 0) {
            index--;
            this.notifyObservers("\033[D");
        }
    }

    public void moveToRight() {
        System.out.println(inputText.length());
        if (index < inputText.length()) {
            index++;
            this.notifyObservers("\033[C");
        }
    }

    public void moveToStart() {
        index = 0;
    }

    public void moveToEnd() {
        index = inputText.length();
    }

    public void supr() {
        if (index > 0) {
            inputText.deleteCharAt(index - 1);
            index--;
        }
    }

    public void switchInsert() {
        insertMode = !insertMode;
        this.notifyObservers("\033[2~");
    }

    public void add(char input) {
        inputText.append(input);
    }

    public String getText() {
        return inputText.toString();
    }
}
