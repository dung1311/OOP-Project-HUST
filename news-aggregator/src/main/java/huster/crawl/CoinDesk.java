package huster.gui;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class CoinDesk {
    public static void main(String[] args) {

        ItemWebs coinDesk = new ItemWebs();

        coinDesk.setUrl("https://www.coindesk.com/");
        coinDesk.setDomain("https://www.coindesk.com");
        coinDesk.setMainTag("div[class=\"containerstyles__StyledContainer-sc-292snj-0 KqMZq\"]");
        coinDesk.setLinkTag("a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link");

        List<String> linkList = CrawlWeb.getLink(coinDesk);
        List<ItemNews> dataList = new ArrayList<>();

        coinDesk.setAuthorTag("div[class=at-authors]");
        coinDesk.setCategoryTag("div[class=at-category]");
        coinDesk.setDateTag("div[class=at-created label-with-icon]");
        coinDesk.setTitleTag("div[class=at-headline]");
        coinDesk.setSummaryTag("div[class=at-subheadline]");

        for (int i = 0; i < linkList.size(); i++) {
            dataList.add(CrawlWeb.getDataFromLink(coinDesk, linkList.get(i)));
        }

        Gson dataJson = new Gson();
        try {
            FileWriter test = new FileWriter("news-aggregator\\recourse\\data\\data.json");
            test.write(dataJson.toJson(dataList));
            test.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
