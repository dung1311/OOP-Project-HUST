package huster.crawl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class CrawlLink {
    private CrawlLink() {}
    public static List<String> getLink(String url){
        List<String> linkList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements getAll = doc.select("div[class=defaultstyles__FeaturedContentWrapper-sc-1xxogbw-0 cNBGyv]");
            Elements links = getAll.select("a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link");
            for(int i = 0; i < links.size(); i++){
                linkList.add("https://www.coindesk.com" + links.get(i).attr("href"));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return linkList;
    }

    public static void main(String[] args) {
        String url = "https://www.coindesk.com/";
        List<String> test = new ArrayList<>();
        List<Items> itemList = new ArrayList<>();
        test = CrawlLink.getLink(url);
        for(int i=0; i<test.size(); i++){
            Items temp = GetDataFormLink.getData(test.get(i));
            itemList.add(temp);
        }
        Gson json = new Gson();
        String dataJson = json.toJson(itemList);
        System.out.println(dataJson);
    }


}
