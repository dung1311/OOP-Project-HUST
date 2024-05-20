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
        if(file.getDataList("https://www.coindesk.com/") != null)
        this.dataList.addAll(file.getDataList("https://www.coindesk.com/"));
    }

    public void crawlTheBlock()
    {
        DataFromTheBlock file = new DataFromTheBlock();
        this.dataList.addAll(file.getDataList("https://www.theblock.co/"));
    }

    public void crawlNewsBTC() 
    {
        DataFromNewsBTC file = new DataFromNewsBTC();
        if(file.getDataList("https://www.newsbtc.com/") != null)
        this.dataList.addAll(file.getDataList("https://www.newsbtc.com/"));
    }

}
