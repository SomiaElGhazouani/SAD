package Practica_1.WithMVC;

public class Line {

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
            System.out.print("\033[D");
        }
    }

    public void moveToRight() {
        if (index < inputText.length()) {
            index++;
            System.out.print("\033[C");
        }
    }

    public void moveToStart() {
        if (index > 0) {
            index = 0;
            System.out.print("\033[1G");
        }
    }

    public void moveToEnd() {
        if (index < inputText.length()) {
            int diff = inputText.length() - index;
            index = inputText.length();
            System.out.print("\033[" + diff + "C");
        }
    }

    public void supr() {
        if (index >= 0 && index < (inputText.length())) {
            System.out.print("\033[P");
            System.out.print("\033[D");
            inputText.deleteCharAt(index);
        }
    }

    public void switchInsert() {
        insertMode = !insertMode;
        System.out.print("\033[2~");
    }

    public void backspace() {
        if (index > 0) {
            System.out.print("\033[D");
            System.out.print("\033[P");
            inputText.deleteCharAt(index - 1);
            index--;
        }
    }

    public void add(char input) {
        if (insertMode) {
            inputText.setCharAt(index, input);
        } else {
            inputText.insert(index, input);
        }
        index++;

    }

    public String getText() {
        return inputText.toString();
    }
}
