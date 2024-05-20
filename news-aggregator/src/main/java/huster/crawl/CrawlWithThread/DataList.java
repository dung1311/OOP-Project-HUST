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
        if(file.getDataList("https://www.coindesk.com","a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link") != null)
            this.dataList.addAll(file.getDataList("https://www.coindesk.com","a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link"));
    }

    public void crawlTheBlock()
    {
        DataFromTheBlock file = new DataFromTheBlock();
        if(file.getDataList("https://www.theblock.co/") != null)
            this.dataList.addAll(file.getDataList("https://www.theblock.co/"));
    }

    public void crawlNewsBTC() 
    {
        DataFromNewsBTC file = new DataFromNewsBTC();
        if(file.getDataList("https://www.newsbtc.com/") != null)
            this.dataList.addAll(file.getDataList("https://www.newsbtc.com/"));
    }

}
