package huster;

import java.io.IOException;

import huster.crawl.dataFormat.Data;
public class Main {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis(); 
        new Data().crawl();
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("Running Time: " + runTime); 
    }
}