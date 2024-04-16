package huster.action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import huster.gui.News;



public class SetPropertiesElements {
    // 
    private List<JsonObject> newsElements = new ArrayList<>(10);
    private String dataFilePath = "news-aggregator\\resource\\data\\totalData.json";
    // store news from database
    private JsonArray newsArray = new JsonArray();
    

    public List<JsonObject> getNewsElements() {
        return newsElements;
    }   

    public SetPropertiesElements() {
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
        while (randomSet.size() < 10) {
            int randomNumber = random.nextInt(100);
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
                News news = new News();
                    news.setVisible(true);
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

        return jPanel;
    }
}


