package WeatherExample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WeatherOfArea {
    public void weatherOfArea(String url)
    {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements temperature = doc.getElementsByClass("temp");
            Elements infomation = doc.getElementsByClass("value");
            Elements nightTemperature = doc.select("b");
            Elements guessElements = doc.getElementsByClass("body-item");
            Element guess = guessElements.get(1);
            
            
            System.out.println("DayTemperature: " + temperature.get(0).text());
            System.out.println("NightTemperature: " + nightTemperature.get(0).text());
            System.out.println("Wind: " + infomation.get(0).text());
            System.out.println("Wind Guest: " + infomation.get(1).text());
            System.out.println("Air Quality: " + infomation.get(2).text());
            System.out.println("Weather in next day: " + guess.text());
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
