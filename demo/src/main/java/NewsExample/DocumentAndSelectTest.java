package NewsExample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DocumentAndSelectTest {
    String url ;
    List<String> topSearching = new ArrayList<>();
    public List<String> getTopSearching(String url)
    {         
        try{
            Document doc = Jsoup.connect(url).timeout(6000).get();
            Elements datetimeTop = doc.getElementsByClass("sc-e8c85b6-2 skrbn");
            Elements titleTop = doc.getElementsByClass("sc-e8c85b6-3 kYovGM");
            Elements contentTop = doc.getElementsByClass("sc-e8c85b6-4 gUXIHd");
            
            topSearching.add(datetimeTop.text());
            topSearching.add(titleTop.text());
            topSearching.add(contentTop.text());
            return topSearching;
        }catch(Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    public Elements getDatetime(String url) 
    {
        try{
            Document doc = Jsoup.connect(url).timeout(6000).get();
            Elements datetime = doc.getElementsByClass("sc-6a06f16d-4 inEEye");
            return datetime;
        } catch(Exception e)
        {
            return null;
        }
    }
    public Elements getTitle(String url) 
    {
        try{
            Document doc = Jsoup.connect(url).timeout(6000).get();
            Elements title = doc.getElementsByClass("sc-6a06f16d-5 bYQOlh");
            return title;
        } catch(Exception e)
        {
            return null;
        }
    }
    public Elements getContent(String url) 
    {
        try{
            Document doc = Jsoup.connect(url).timeout(6000).get();
            Elements content = doc.getElementsByClass("sc-6a06f16d-6 lenJCM");
            return content;
        } catch(Exception e)
        {
            return null;
        }
    }

    public void selectFromDocument(String url)
    {
        try{
            Document doc = Jsoup.connect(url).timeout(6000).get();
            Elements datetime = doc.getElementsByClass("sc-6a06f16d-4 inEEye");
            Elements title = doc.getElementsByClass("sc-6a06f16d-5 bYQOlh");
            Elements content = doc.getElementsByClass("sc-6a06f16d-6 lenJCM");
            // int titleSize = title.size();
            // int contentSize = content.size();
            // int i = 0;
            // int j = 0;
            Elements datetimeTop = doc.getElementsByClass("sc-e8c85b6-2 skrbn");
            Elements titleTop = doc.getElementsByClass("sc-e8c85b6-3 kYovGM");
            Elements contentTop = doc.getElementsByClass("sc-e8c85b6-4 gUXIHd");
            System.out.println(datetimeTop.text());
            System.out.println(titleTop.text());
            System.out.println(contentTop.text());
            System.out.println("----------------------------------------------");
            for(int i = 0; i < title.size(); i++)
            {
                System.out.println(datetime.get(i).text());
                System.out.println(title.get(i).text());
                System.out.println(content.get(i).text());
                System.err.println("----------------------------------------------");
                
            }
        } catch(Exception e){
            System.out.println("Error");
        }
    }

}
