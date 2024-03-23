package NewsExample;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConnectionResponseParse {
    String url;
    public void responseParse(String url)
    {
        try{
            Connection.Response response = Jsoup.connect(url).execute();
            Document doc = response.parse();
            String title = doc.title();
            System.out.println("Title: " + title);
    
        }
        catch (Exception e){
            System.out.println("error");
        }
    }
    
        
}
