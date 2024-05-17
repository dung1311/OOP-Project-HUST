package huster.gui;

import java.util.Stack;


public class SearchUIHistory {
    private static SearchUIHistory instance;
    private Stack<SearchUI> historyStack;

    private SearchUIHistory() {
        historyStack = new Stack<>();
    }

    public static SearchUIHistory getInstance() {
        if (instance == null) {
            instance = new SearchUIHistory();
        }
        return instance;
    }

    public void pushScreen(SearchUI screen) {
        historyStack.push(screen);
    }

    public SearchUI popScreen() {
        return historyStack.pop();
    }

    public SearchUI peekScreen() {
        return historyStack.peek();
    }

    public boolean isEmpty() {
        return historyStack.isEmpty();
    }
}
