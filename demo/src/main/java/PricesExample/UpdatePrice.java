package PricesExample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class UpdatePrice {
    String url;

    public Elements getIdOfCoin(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements idOfCoin = doc.getElementsByClass("sc-89fc2ff1-6 crmkSt");
            return idOfCoin;
        } catch (Exception e) {
            return null;
        }
    }

    public Elements getNameOfCoin(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements nameOfCoin = doc.getElementsByClass("sc-89fc2ff1-5 fYsYrO");
            return nameOfCoin;
        } catch (Exception e) {
            return null;
        }
    }

    public Elements getCurrentPrice(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements currentPrice = doc.getElementsByClass("sc-89fc2ff1-0 iQXnyB");
            return currentPrice;
        } catch (Exception e) {
            return null;
        }
    }

    public void showNewestPrice(String url, int capacity) {
        try {
            Document doc = Jsoup.connect(url).get();

            Elements idOfCoin = doc.getElementsByClass("sc-89fc2ff1-6 crmkSt");
            Elements nameOfCoin = doc.getElementsByClass("sc-89fc2ff1-5 fYsYrO");
            Elements currentPrice = doc.getElementsByClass("sc-89fc2ff1-0 iQXnyB");
            Elements marketCap = doc.getElementsByClass("sc-89fc2ff1-11 cBoudl");
            
            for (int i = 0; i < capacity; i++) {
                System.out.println(idOfCoin.get(i).text());
                System.out.println(nameOfCoin.get(i).text());
                System.out.println(currentPrice.get(i).text());
                System.out.println(marketCap.get(i).text());
                System.out.println("-------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showNewestPrice(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            Elements idOfCoin = doc.getElementsByClass("sc-89fc2ff1-6 crmkSt");
            Elements nameOfCoin = doc.getElementsByClass("sc-89fc2ff1-5 fYsYrO");
            Elements currentPrice = doc.getElementsByClass("sc-89fc2ff1-0 iQXnyB");
            Elements marketCap = doc.getElementsByClass("sc-89fc2ff1-11 cBoudl");
            for (int i = 0; i < idOfCoin.size(); i++) {
                System.out.println(idOfCoin.get(i).text());
                System.out.println(nameOfCoin.get(i).text());
                System.out.println(currentPrice.get(i).text());
                System.out.println(marketCap.get(i).text());
                System.out.println("-------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
