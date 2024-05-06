package huster;

import java.io.IOException;

import huster.crawl.ServerUtility;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerUtility.shutDownServer();
    }
}