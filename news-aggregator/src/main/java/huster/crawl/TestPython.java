package huster.crawl;

import org.python.util.PythonInterpreter;
import org.python.core.*;

class test3 {
    public static void main(String[] args) {
        System.setProperty("python.console.encoding", "UTF-8");
        System.setProperty("python.import.site", "false");

        try (PythonInterpreter python = new PythonInterpreter()) {
            python.execfile("news-aggregator\\src\\main\\java\\huster\\crawl\\CrawlTwitter.py");
            PyObject drawTableFunction = python.get("draw_table");
            python.set("file_name", "abcd");
            drawTableFunction.__call__(python.get("abcd"));
        }
    }

}