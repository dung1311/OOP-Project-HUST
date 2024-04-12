package huster.crawl.CrawlWithThread;

import java.util.ArrayList;
import java.util.List;

import huster.crawl.DataFormat.Data;
import huster.crawl.coinDesk.DataFromCoinDesk;
import huster.crawl.newsBTC.DataFromNewsBTC;
import huster.crawl.theBlock.DataFromTheBlock;

public class DataList {
    private List<Data> dataList = new ArrayList<>();
    
    public List<Data> getDataList() {
        return dataList;
    }

    public void crawlFromCoinDesk() 
    {
        DataFromCoinDesk file = new DataFromCoinDesk();
        this.dataList.addAll(file.writeOnJsonFile("https://www.coindesk.com/"));
    }

    public void crawlTheBlock()
    {
        DataFromTheBlock file = new DataFromTheBlock();
        this.dataList.addAll(file.writeOnJsonFile("https://www.theblock.co/"));
    }

    public void crawlNewsBTC() 
    {
        DataFromNewsBTC file = new DataFromNewsBTC();
        this.dataList.addAll(file.writeOnJsonFile("https://www.newsbtc.com/"));
    }

}
