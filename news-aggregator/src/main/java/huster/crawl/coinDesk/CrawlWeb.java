package huster.crawl.coinDesk;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CrawlWeb {
    private CrawlWeb() {
    }

    public static List<String> getLink(ItemWebs tempWeb) {
        List<String> linkList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(tempWeb.getUrl()).get();
            Elements getAll = doc.select(tempWeb.getMainTag());
            Elements links = getAll.select(tempWeb.getLinkTag());
            for (int i = 0; i < links.size(); i++) {
                linkList.add(tempWeb.getDomain() + links.get(i).attr("href"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkList;
    }

    public static ItemNews getDataFromLink(ItemWebs tempWeb, String url) {
        ItemNews data = new ItemNews();
        try {
            Document doc = Jsoup.connect(url).get();
            data.setLink(url);
            data.setTitle(doc.select(tempWeb.getTitleTag()).text());
            data.setCategory(doc.select(tempWeb.getCategoryTag()).text());
            data.setSummary(doc.select(tempWeb.getSummaryTag()).text());
            data.setDate(doc.select(tempWeb.getDateTag()).text());
            data.setAuthor(doc.select(tempWeb.getAuthorTag()).text());
            data.setHashTag(doc.select(tempWeb.getHashTag()).text());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<ItemNews> getDataList(ItemWebs tempWeb) {
        List<ItemNews> dataList = new ArrayList<>();
        List<String> linkList = new ArrayList<>();
        linkList = getLink(tempWeb);
        for (int i = 0; i < linkList.size(); i++) {
            ItemNews temp = new ItemNews();
            temp = getDataFromLink(tempWeb, linkList.get(i));
            dataList.add(temp);
        }
        return dataList;
    }
}
