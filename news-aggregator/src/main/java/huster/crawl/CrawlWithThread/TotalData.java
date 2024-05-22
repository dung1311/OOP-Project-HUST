package huster.crawl.crawlWithThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import huster.crawl.dataFromWebsite.*;
import huster.crawl.dataFormat.Data;

public class TotalData {
    private List<Data> totalData = new ArrayList<>();
    public static int COUNT_SOURCE = 0;

    public List<Data> getDataList() {
        return totalData;
    }

    public void setTotalData(List<Data> totalData) {
        this.totalData = totalData;
    }

    public static void setCOUNT_SOURCE(String path) { 
        File packageDir = new File(path);
        if (packageDir.exists() && packageDir.isDirectory()) {
            for (File file : packageDir.listFiles()) {
                System.out.println(file.getName());
                if (file.isFile() && file.getName().endsWith(".java")) {
                    COUNT_SOURCE++;
                }
            }
        }
    }

    public void setDataList() {
        DataList runnableToGeDataList = new DataList();
        TotalData.setCOUNT_SOURCE("news-aggregator/src/main/java/huster/crawl/dataFromWebsite");
        CountDownLatch latch = new CountDownLatch(COUNT_SOURCE);
        Thread crawlFromCoinDeskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataFromCoinDesk data = new DataFromCoinDesk();
                runnableToGeDataList.addNewsCrawlThread(data,"https://www.coindesk.com","a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link","href");
                latch.countDown();
            }
        });

        Thread crawlFromNewsBTCThread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataFromNewsBTC data = new DataFromNewsBTC();
                runnableToGeDataList.addNewsCrawlThread(data,"https://www.newsbtc.com","pageable-container","data-page");
                latch.countDown();
            }
        });

        Thread crawlFrom101Blockchains = new Thread(new Runnable() {
            @Override
            public void run() {
                DataFrom101Blockchains data = new DataFrom101Blockchains();
                runnableToGeDataList.addNewsCrawlThread(data,"https://101blockchains.com/blog/","a[rel=bookmark]","href");
                latch.countDown();
            }
        });

        crawlFromCoinDeskThread.start();
        crawlFromNewsBTCThread.start();
        crawlFrom101Blockchains.start();
        try {
            latch.await(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalData.addAll(runnableToGeDataList.getDataList());
    }

}

