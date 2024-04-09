package huster;

import huster.gui.Menu;
import huster.gui.ScreenHistory;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        ScreenHistory.getInstance().pushScreen(menu);
        menu.setVisible(true);
    }
}