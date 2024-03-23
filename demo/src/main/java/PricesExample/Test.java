package PricesExample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

//  import java.io.IOException;

public class Test  {
    public void show(String url)
    {
        try{
            Document doc = Jsoup.connect(url).get();

            Elements datetime = doc.getElementsByClass("sc-6a06f16d-4 inEEye");


            System.out.println(datetime.text());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
}
