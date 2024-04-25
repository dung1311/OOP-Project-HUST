package huster.action;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import huster.gui.News;



public class GetData {
     
    private List<JsonObject> newsElements = new ArrayList<>(10);
    // private String dataFilePath = "news-aggregator\\resource\\data\\totalData.json";
    private String dataFilePath = "news-aggregator\\resource\\data\\dataCoinDesk.json";

    // store news from database
    private JsonArray newsArray = new JsonArray();
    

    public List<JsonObject> getNewsElements() {
        return newsElements;
    }   

    public GetData() {
        init();
    }

    public void init(){
        JsonArray m_newsArray = new JsonArray();
        // read data and asgin to newsArray
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFilePath));

            m_newsArray = new Gson().fromJson(bufferedReader, JsonArray.class);
            newsArray = m_newsArray;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<Integer> randomSet = new HashSet<>();
        Random random = new Random();
        //get radom index from newsArray
        while (randomSet.size() < 30) {
            // random number
            int randomNumber = random.nextInt(45);
            randomSet.add(randomNumber);
        }
        // add news Elements into list
        for(int i : randomSet){
            newsElements.add(newsArray.get(i).getAsJsonObject());
        }

    }   

    public JPanel set(String urLString, String textString) {
        JPanel jPanel = new JPanel();
        JButton articleJButton = new JButton();

        articleJButton.setContentAreaFilled(false);
        articleJButton.setBorderPainted(false);
        articleJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                News news = new News("Dung", "10-10-2024", "url.url", "Em yeu anh tinh yeu cua em tua ngan tia nang, anh lai la gay", "day la title");
                news.setVisible(true);
                System.out.println("clicked");    
            }
        });

        try {
            Image image =  HandleImage.resizeImage(urLString);
            articleJButton.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel articleLable = new JLabel(textString);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.setPreferredSize(new Dimension(477, 268));
        jPanel.setVisible(true);
        jPanel.add(articleJButton);
        jPanel.add(articleLable);

        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("hehehe");
            }   
        });



        return jPanel;
    }

}

