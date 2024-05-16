package huster.gui;

import java.util.Stack;

import javax.swing.JFrame;

public class ScreenHistory {
    private static ScreenHistory instance;
    private Stack<JFrame> historyStack;

    private ScreenHistory() {
        historyStack = new Stack<>();
    }

    public static ScreenHistory getInstance() {
        if (instance == null) {
            instance = new ScreenHistory();
        }
        return instance;
    }

    public void pushScreen(JFrame screen) {
        historyStack.push(screen);
    }

    public JFrame popScreen() {
        return historyStack.pop();
    }

    public boolean isEmpty() {
        return historyStack.isEmpty();
    }
}
