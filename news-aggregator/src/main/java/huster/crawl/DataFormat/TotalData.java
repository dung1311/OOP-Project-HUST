package huster.crawl.dataFormat;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import huster.crawl.dataFromWebsite.*;

public class TotalData {
    private List<Data> totalData = new ArrayList<>(); //One List which save data in every website which crawled
    public static int COUNT_SOURCE = 0;

    public List<Data> getTotalData() {
        return totalData;
    }

    public void setTotalData(List<Data> totalData) {
        this.totalData = totalData;
    }
    //This method set a quantity of java file in the folder which pointed to by the path to COUNT_SOURCE
    private static void setCOUNT_SOURCE(String path) { 
        File packageDir = new File(path);
        if (packageDir.exists() && packageDir.isDirectory()) {
            for (File file : packageDir.listFiles()) {
                // System.out.println(file.getName());
                if (file.isFile() && file.getName().endsWith(".java")) {
                    COUNT_SOURCE++;
                }
            }
        }
    }

    //This method appends a stream with the task of adding an element of data type Data to a list
    public void addDataList(DataListFormat dataListFormat, DataListFormat itemLink, String url, String innerLinkClass, String innerLinkAttr) 
    {
        List<Data> dataList = dataListFormat.getDataList(itemLink, url,innerLinkClass,innerLinkAttr);
        if( dataList != null)
            this.totalData.addAll(dataList);
    }

    //This method invokes parallel streams to append elements of data type Data to a list
    public void setDataList() {
        TotalData runnableToGetDataList = new TotalData();
        TotalData.setCOUNT_SOURCE("news-aggregator/src/main/java/huster/crawl/dataFromWebsite");
        CountDownLatch latch = new CountDownLatch(COUNT_SOURCE);//
        Thread crawlFromCoinDeskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataFromCoinDesk data = new DataFromCoinDesk();
                DataFromCoinDesk itemLink = new DataFromCoinDesk();
                runnableToGetDataList.addDataList(data,itemLink,"https://www.coindesk.com","a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link","href");
                latch.countDown();
            }
        });

        Thread crawlFromNewsBTCThread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataFromNewsBTC data = new DataFromNewsBTC();
                DataFromNewsBTC itemLink = new DataFromNewsBTC();
                runnableToGetDataList.addDataList(data,itemLink,"https://www.newsbtc.com","pageable-container","data-page");
                latch.countDown();
            }
        });

        Thread crawlFromBlogChainLink = new Thread(new Runnable() {
            @Override
            public void run() {
                DataFromBlogChainLink data = new DataFromBlogChainLink();
                DataFromBlogChainLink itemLink = new DataFromBlogChainLink();
                runnableToGetDataList.addDataList(data,itemLink,"https://blog.chain.link/","post-card-href", "href");
                latch.countDown();
            }
        });

        ArrayList<Thread> threadsFrom101Blockchains = new ArrayList<>();
        for(int i = 1; i < 10; i++ ) {
            int j = i;
            Thread crawlFrom101Blockchains = new Thread(new Runnable() {
                @Override
                public void run() {
                    DataFrom101Blockchains data = new DataFrom101Blockchains();
                    DataFrom101Blockchains itemLink = new DataFrom101Blockchains();
                    String pageNumber = String.valueOf(j);
                    runnableToGetDataList.addDataList(data,itemLink,"https://101blockchains.com/blog/page/" + pageNumber,"a[rel=bookmark]","href");
                    if(j == 9)    
                        latch.countDown();
                }
            });
            threadsFrom101Blockchains.add(crawlFrom101Blockchains);
        }

        ArrayList<Thread> threadsFromInvestopedia = new ArrayList<>();
        for(int i = 1; i < 10; i++ ) {
            int j = i-1;
            Thread crawlFromInvestopedia = new Thread(new Runnable() {
                @Override
                public void run() {
                    DataFromInvestopedia data = new DataFromInvestopedia();
                    DataFromInvestopedia itemLink = new DataFromInvestopedia();
                    String pageNumber = String.valueOf(10*j);
                    runnableToGetDataList.addDataList(data,itemLink,"https://www.investopedia.com/search?q=blockchain&offset=" + pageNumber,"a[class=search-results__link mntl-text-link ]","href");
                    if(j == 8)    
                        latch.countDown();
                }
            });
            threadsFromInvestopedia.add(crawlFromInvestopedia);
        }

        Thread crawlFromSouthChinaMorningPost = new Thread(new Runnable() {
            @Override
            public void run() {
                DataFromSouthChinaMorningPost data = new DataFromSouthChinaMorningPost();
                DataFromSouthChinaMorningPost itemLink = new DataFromSouthChinaMorningPost();
                runnableToGetDataList.addDataList(data,itemLink,"https://www.scmp.com/topics/blockchain","a.e1whfq0b2.css-8ug9pk.ef1hf1w0","href");
                latch.countDown();
            }
        });

        crawlFromCoinDeskThread.start();
        crawlFromNewsBTCThread.start();
        crawlFromBlogChainLink.start();
        crawlFromSouthChinaMorningPost.start();
        for(int i = 0; i < 9; i++) {
            threadsFromInvestopedia.get(i).start();
        }
        for(int j = 0; j < 9; j++) {
            threadsFrom101Blockchains.get(j).start();
        }

        try {
            latch.await(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        } //Waiting for all of stream finish
        totalData.addAll(runnableToGetDataList.getTotalData());
    }

    public void crawl() {
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            TotalData dataList = new TotalData();
            dataList.setDataList();
            String json = gson.toJson(dataList.getTotalData());
    
            FileWriter fileWriter = new FileWriter("news-aggregator/resource/data/totalData.json",Charset.forName("UTF-8"));
            fileWriter.write(json);
            fileWriter.close();
            System.out.println("Successful!!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }   
}

