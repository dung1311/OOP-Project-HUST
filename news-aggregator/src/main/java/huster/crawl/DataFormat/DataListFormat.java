package huster.crawl.DataFormat;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import huster.crawl.DataFormat.Data;
public abstract class DataListFormat {
    void getDataList(String url) {}

}


