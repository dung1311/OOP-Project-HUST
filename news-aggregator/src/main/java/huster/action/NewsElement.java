package huster.action;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.google.gson.JsonObject;

public class NewsElement {
    private List<JPanel> newsList = new ArrayList<>();
    private List<JsonObject> dataJsonObjects = new ArrayList<>();
    

    public List<JPanel> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<JPanel> newsList) {
        this.newsList = newsList;
    }

    public NewsElement() {
        GetData data = new GetData();
        dataJsonObjects = data.getNewsElements();
    }
    
    public JPanel articleJPanel(){
        JPanel containerJPanel = new JPanel();
        containerJPanel.setLayout(new BoxLayout(containerJPanel, BoxLayout.Y_AXIS));

        // JButton ImageButton = new JButton();


        return null;
    }
    

    public void test(){
        System.out.println(dataJsonObjects.get(0).toString());
    }
}


class Test {

    public static void main(String[] args) {
        NewsElement test = new NewsElement();
        test.test();
    }
}