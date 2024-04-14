package huster.action;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class VisulizeNews {
    private static List<JsonObject> newsObjects = new ArrayList<>(10);
    private static JsonArray newsArray = new JsonArray();
    private static String dataFilePath = "news-aggregator\\resource\\data\\totalData.json";

    public static void init(){
        JsonArray _newsArray = new JsonArray();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFilePath));

            _newsArray = new Gson().fromJson(bufferedReader, JsonArray.class);
            newsArray = _newsArray;
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

        for(int i : randomSet){
            newsObjects.add(newsArray.get(i).getAsJsonObject());
        }

        for(JsonObject object : newsObjects){
            System.out.println(object.toString());
        }
    }   

    public static ImageIcon resizeImage() {
        Image image = null;
        try {
            image = ImageIO.read(new URL("https://www.newsbtc.com/wp-content/uploads/2024/04/Shiba-Inu-2.jpeg?fit%5Cu003d1200%2C684"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image newImage = image.getScaledInstance(476, 268, Image.SCALE_SMOOTH);

        return new ImageIcon(newImage);
    }

}

class Main1  {
    public static void main(String[] args) {
        // VisulizeNews.init();
        // ImageIcon test = new ImageIcon("news-aggregator\\resource\\assets\\minhchung.png");
        ImageIcon test = null;
        Image testIcon = null;
        try {
            testIcon = ImageIO.read(new URL("https://www.newsbtc.com/wp-content/uploads/2024/04/Shiba-Inu-2.jpeg?fit%5Cu003d1200%2C684"));
            test = new ImageIcon(testIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon testRes = VisulizeNews.resizeImage();
        
        JFrame mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(1000, 1000);
        mainFrame.setLayout(new GridLayout(1, 2, 10, 10));
        JButton testButton = new JButton(test);
        JButton testResButton = new JButton(testRes);
        mainFrame.add(testButton);
        mainFrame.add(testResButton);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    
}
