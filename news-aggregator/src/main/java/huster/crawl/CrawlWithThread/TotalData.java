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

        Thread crawlFromBlogChainLink = new Thread(new Runnable() {
            @Override
            public void run() {
                DataFromBlogChainLink data = new DataFromBlogChainLink();
                runnableToGeDataList.addNewsCrawlThread(data,"https://blog.chain.link/","post-card-href", "href");
                latch.countDown();
            }
        });

        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 1; i < 4; i++ ) {
            int j = i;
            Thread crawlFrom101Blockchains = new Thread(new Runnable() {
                @Override
                public void run() {
                    DataFrom101Blockchains data = new DataFrom101Blockchains();
                    String pageNumber = String.valueOf(j);
                    runnableToGeDataList.addNewsCrawlThread(data,"https://101blockchains.com/blog/" + pageNumber,"a[rel=bookmark]","href");
                    if(j == 3)    
                        latch.countDown();
                }
            });
            threads.add(crawlFrom101Blockchains);
        }

        // Thread crawlFrom101Blockchains_page_2 = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         DataFrom101Blockchains data = new DataFrom101Blockchains();
        //         runnableToGeDataList.addNewsCrawlThread(data,"https://101blockchains.com/blog/page/2/","a[rel=bookmark]","href");
        //     }
        // });

        // Thread crawlFrom101Blockchains_page_3 = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         DataFrom101Blockchains data = new DataFrom101Blockchains();
        //         runnableToGeDataList.addNewsCrawlThread(data,"https://101blockchains.com/blog/page/3","a[rel=bookmark]","href");
        //         latch.countDown();
        //     }
        // });

        crawlFromCoinDeskThread.start();
        crawlFromNewsBTCThread.start();
        crawlFromBlogChainLink.start();
        for(int j = 0; j < 3; j++) {
            threads.get(j).start();
        }
        
        // crawlFrom101Blockchains_page_1.start();
        // crawlFrom101Blockchains_page_2.start();
        // crawlFrom101Blockchains_page_3.start();
        try {
            latch.await(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalData.addAll(runnableToGeDataList.getDataList());
    }

}

