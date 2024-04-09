package huster.gui;

import java.util.Stack;

import javax.swing.JFrame;

public class ScreenHistoryManager {
    private Stack<JFrame> screenHistory;

    public ScreenHistoryManager() {
        screenHistory = new Stack<>();
    }

    public void pushFrame(JFrame frame) {
        screenHistory.push(frame);
    }

    public JFrame popFrame() {
        return screenHistory.pop();
    }

    public JFrame peekFrame() {
        return screenHistory.peek();
    }

    public boolean isEmpty() {
        return screenHistory.isEmpty();
    }

    public void printHistory() {
        System.out.println("Frame History:");
        for (JFrame frame : screenHistory) {
            System.out.println(frame);
        }
    }

    // public static void main(String[] args) {
    //     ScreenHistoryManager screenHistoryManager = new ScreenHistoryManager();
    //     screenHistoryManager.printHistory();
    // }
}
