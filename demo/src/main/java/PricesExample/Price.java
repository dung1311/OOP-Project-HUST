package PricesExample;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Price {
    public void showPrice(String url,int capacity)
    {
        try{
            Document doc = Jsoup.connect(url).get();

            List<String> IdOfCoin = new ArrayList<>();
            List<String> nameOfCoin = new ArrayList<>();
            List<String> currentPrice = new ArrayList<>();
            List<String> changeOfPrice = new ArrayList<>();
            Elements check = doc.getElementsByClass("sc-89fc2ff1-22 hZgppT");

            for(int i = 0; i < capacity; i++)
            {
                Element curElement = check.get(i);
                IdOfCoin.add(curElement.getElementsByClass("sc-89fc2ff1-6 crmkSt").text());
                nameOfCoin.add(curElement.getElementsByClass(("sc-89fc2ff1-5 fYsYrO")).text());
                currentPrice.add(curElement.getElementsByClass("sc-89fc2ff1-0 iQXnyB").text());
                // if(curElement.getElementsByClass("sc-89fc2ff1-13 fnuBgf") != null)
                // {
                    changeOfPrice.add(curElement.getElementsByClass("sc-89fc2ff1-13 fnuBgf").text());
                //}
                // if(curElement.getElementsByClass("sc-89fc2ff1-13 eiVIQs") != null){
                    changeOfPrice.add(curElement.getElementsByClass("sc-89fc2ff1-13 eiVIQs").text());
                //}

            }
            
            for(int i = 0; i < IdOfCoin.size(); i++)
            {
                System.out.println(IdOfCoin.get(i));
                System.out.println(nameOfCoin.get(i));
                System.out.println(currentPrice.get(i));
                System.out.println(changeOfPrice.get(i));
                System.err.println("----------------------------------------------------------------------------------------------");

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
