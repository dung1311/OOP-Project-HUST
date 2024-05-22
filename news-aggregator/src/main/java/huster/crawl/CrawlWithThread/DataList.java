package huster.crawl.crawlWithThread;

import java.util.ArrayList;
import java.util.List;

import huster.crawl.dataFormat.Data;
import huster.crawl.dataFormat.DataListFormat;


public class DataList {
    private List<Data> dataList = new ArrayList<>();
    private String url;
    private String innerLinkClass;
    private String innerLinkAttr;
    
    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInnerLinkClass() {
        return innerLinkClass;
    }

    public void setInnerLinkClass(String innerLinkClass) {
        this.innerLinkClass = innerLinkClass;
    }

    public String getInnerLinkAttr() {
        return innerLinkAttr;
    }

    public void setInnerLinkAttr(String innerLinkAttr) {
        this.innerLinkAttr = innerLinkAttr;
    }

    public void addNewsCrawlThread(DataListFormat dataListFormat, String url, String innerLinkClass, String innerLinkAttr) 
    {
        List<Data> dataList = dataListFormat.getDataList(url,innerLinkClass,innerLinkAttr);
        if( dataList != null)
            this.dataList.addAll(dataList);
    }

}

