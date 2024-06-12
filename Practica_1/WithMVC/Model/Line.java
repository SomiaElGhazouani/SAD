package Practica_1.WithMVC.Model;

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

    public boolean moveToLeft() {
        if (index > 0) {
            index--;
            return true;
        }
        return false;
    }

    public boolean moveToRight() {
        if (index < inputText.length()) {
            index++;
            return true;
        }
        return false;
    }

    public boolean moveToStart() {
        if (index > 0) {
            index = 0;
            return true;
        }
        return false;
    }

    public String moveToEnd() {
        if (index < inputText.length()) {
            int diff = inputText.length() - index;
            index = inputText.length();
            return ("\033[" + diff + "C");
        }
        return "";
    }

    public boolean supr() {
        if (index >= 0 && index < (inputText.length())) {
            inputText.deleteCharAt(index);
            return true;
        }
        return false;
    }

    public void switchInsert() {
        insertMode = !insertMode;
    }

    public boolean backspace() {
        if (index > 0) {
            inputText.deleteCharAt(index - 1);
            index--;
            return true;
        }
        return false;
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
