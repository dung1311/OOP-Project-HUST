package huster.gui;

import java.util.Stack;

public class MenuHistory {
    private static MenuHistory instance;
    private Stack<Menu> historyStack;

    private MenuHistory() {
        historyStack = new Stack<>();
    }

    public static MenuHistory getInstance() {
        if (instance == null) {
            instance = new MenuHistory();
        }
        return instance;
    }

    public void pushScreen(Menu screen) {
        historyStack.push(screen);
    }

    public Menu popScreen() {
        return historyStack.pop();
    }

    public Menu peekScreen() {
        return historyStack.peek();
    }

    public boolean isEmpty() {
        return historyStack.isEmpty();
    }
}