package huster;

import java.util.Stack;

import javax.swing.JFrame;

import huster.gui.Menu;

public class Main {
    public static void main(String[] args) {
        Stack<JFrame> screenHistory = new Stack<>();

        Menu menu = new Menu(screenHistory);
        menu.setVisible(true);
    }
}