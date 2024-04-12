package huster;

//import java.io.FileWriter;



import huster.gui.Menu;
import huster.gui.ScreenHistory;


public class Main {
    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();

        Thread displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.display();
                
            }
        });

        displayThread.start();
    }

}

class MyRunnable {
    public void display() {
        Menu menu = new Menu();
        ScreenHistory.getInstance().pushScreen(menu);
        menu.setVisible(true);
    }

    public void crawl() {

    }
}
