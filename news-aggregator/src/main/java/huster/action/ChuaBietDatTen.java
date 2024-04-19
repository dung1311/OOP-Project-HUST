package huster.action;

import com.google.gson.JsonObject;

public class ChuaBietDatTen {
    
    private String authorName;
    private String link;
    private String content;
    private String linkImage;


    public ChuaBietDatTen(JsonObject jsonObject){
        this.authorName = jsonObject.get("author").getAsString();
        this.content = jsonObject.get("content").getAsString();
        this.link = jsonObject.get("link").getAsString();
        this.linkImage = jsonObject.get("linkImage").getAsString();
    }


    public String getAuthorName() {
        return authorName;
    }

 
    public String getLink() {
        return link;
    }


    public String getContent() {
        return content;
    }

    

}
