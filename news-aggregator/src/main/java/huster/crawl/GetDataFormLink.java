package huster.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetDataFormLink {
    private GetDataFormLink(){}

    public static Items getData(String url){
        Items data = new Items();
        try {
            Document doc = Jsoup.connect(url).get();
            data.setLink(url);
            data.setTitle(doc.select("div[class=\"at-headline\"]").text());
            data.setCategory(doc.select("div[class=\"at-category\"]").text());
            data.setSummary(doc.select("div[class=\"at-subheadline \"]").text());
            data.setDate(doc.select("div[class=\"at-created label-with-icon\"]").text() + " " + doc.select("div[class=\"at-updated\"]").text());
            data.setAuthor(doc.select("span[class=\"typography__StyledTypography-sc-owin6q-0 hirYAs\"]").text());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public static void main(String[] args) {
        String url = "https://www.coindesk.com/markets/2024/03/29/dogwifhat-becomes-third-largest-meme-coin-as-bitcoin-clings-to-70k/";
        getData(url);
        
    }
}
