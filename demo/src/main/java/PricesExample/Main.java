package PricesExample;

public class Main {
    public static void main(String[] args)
    {
        String url = "https://www.blockchain.com/explorer/prices/";
        // Price infoPrice = new Price();
        // infoPrice.showPrice(url);
        // UpdatePrice priceOfCoin = new UpdatePrice();
        // priceOfCoin.showNewestPrice(url,10);

        SaveToJSON save = new SaveToJSON();
        save.saveToJSON(url,1);
        
        // Test test = new Test();
        // test.show("https://www.blockchain.com/blog/");
        
    }

}
