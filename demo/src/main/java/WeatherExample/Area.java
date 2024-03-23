package WeatherExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Area {
    public void getArea(String url)
    {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements area = doc.getElementsByClass("search-result");
            
            for(int i = 2; i < area.size(); i++)
            {
                System.out.print((i - 1) + ": ");
                System.out.println(area.get(i).text());
            }
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Choose your area's number (in that list) to continue");
            
            Element resultContainer = doc.selectFirst(".result-container");
            Elements links = resultContainer.select("a");

            List<String> search = new ArrayList<>();

            for(int i = 0; i < links.size(); i++)
            {
                search.add(links.get(i).attr("href"));
            }

            Scanner sc = new Scanner(System.in);

            int searchInt = sc.nextInt() - 1;
            System.out.println(area.get(searchInt + 2).text());

            System.out.println("-------------------------------------------------------------------------");
            WeatherOfArea weather = new WeatherOfArea();
            weather.weatherOfArea("https://www.accuweather.com" + search.get(searchInt));

            sc.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
