package huster.crawl.actionOfJsonFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Tag {
    private List<String> currentTagList = new ArrayList<>();
    public static List<String> hashtag = new ArrayList<>() ;

    public Tag() {}
    
    public Tag(JsonElement jsonElement) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray currentTag = jsonObject.get("tag").getAsJsonArray();
        if(currentTag == null) return;
        for(JsonElement element : currentTag) {
            this.currentTagList.add(element.getAsString());
        }
    }

    public List<String> getCurrentTagList() {
        return currentTagList;
    }

    public void setCurrentTagList(JsonElement jsonElement) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray currentTag = jsonObject.get("tag").getAsJsonArray();
        if(currentTag == null) return;
        for(JsonElement element : currentTag) {
            this.currentTagList.add(element.getAsString());
        }
    }
    

    public static void setTag(JsonArray jsonArray) {
        Set<String> hashtagSet = new HashSet<>();

        for(JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray currentTag = jsonObject.get("tag").getAsJsonArray();
            if(currentTag == null) continue;
            List<String> currentTagList = new ArrayList<>();
            for(JsonElement element : currentTag) {
                currentTagList.add(element.getAsString());
            }
            for(String eachTag : currentTagList) {
                hashtagSet.add(eachTag);
            }
        }
        hashtag.addAll(hashtagSet);
        Collections.sort(hashtag);
    }

    public boolean isContainTag(String tagSearching) {
        return currentTagList.contains(tagSearching);
    }
}
