package huster.crawl.CrawlWithThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import huster.crawl.DataFormat.Data;

public class TotalData {
    List<Data> totalData = new ArrayList<>();

    public List<Data> getDataList() {
        return totalData;
    }

    public void setDataList() {
        DataList runnableToGeDataList = new DataList();
        CountDownLatch latch = new CountDownLatch(3);
        Thread crawlFromCoinDeskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                runnableToGeDataList.crawlFromCoinDesk();
                latch.countDown();
            }
        });

        Thread crawlFromTheBlockThread = new Thread(new Runnable() {
            @Override
            public void run() {
                runnableToGeDataList.crawlTheBlock();
                latch.countDown();
            }
        });

        Thread crawlFromNewsBTCThread = new Thread(new Runnable() {
            @Override
            public void run() {
                runnableToGeDataList.crawlNewsBTC();
                latch.countDown();
            }
        });

        crawlFromCoinDeskThread.start();
        crawlFromTheBlockThread.start();
        crawlFromNewsBTCThread.start();
        try {
            latch.await(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalData.addAll(runnableToGeDataList.getDataList());
    }


}

