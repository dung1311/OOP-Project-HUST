package huster.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SearchData {
    // store value of json file
    private JsonArray _JsonArray = new JsonArray();

    public JsonArray get_JsonArray() {
        return _JsonArray;
    }

    public SearchData(String dataPath) {
        init(dataPath);
    }

    public void init(String dataPath) {
        JsonArray m_newsArray = new JsonArray();
        // read data and a sign to newsArray
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataPath));

            m_newsArray = new Gson().fromJson(bufferedReader, JsonArray.class);
            _JsonArray = m_newsArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String findingText = new String();
    private List<JsonObject> result = new ArrayList<>();

    public List<JPanel> search(String findText) {
        int length = get_JsonArray().size();
        List<JPanel> jPanels = new ArrayList<>();

        List<JsonObject> s_JsonObjects = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            s_JsonObjects.add(_JsonArray.get(i).getAsJsonObject());
        }

        for (int i = 0; i < s_JsonObjects.size(); i++) {
            String jsonString = new String();
            jsonString = s_JsonObjects.get(i).get("title").toString();
            jsonString += s_JsonObjects.get(i).get("summary").toString();
            jsonString += s_JsonObjects.get(i).get("tag").toString();
            if (jsonString.contains(findText)) {
                result.add(s_JsonObjects.get(i));
            }
        }

        for (int i = 0; i < result.size(); i++) {
            JPanel jPanel = new newsObject(result.get(i)).setAsJPanel();
            jPanels.add(jPanel);
        }

        return jPanels;
    }

    public List<String> searchAsLink(String findText) {
        int length = get_JsonArray().size();
        List<String> links = new ArrayList<>();

        List<JsonObject> s_JsonObjects = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            s_JsonObjects.add(_JsonArray.get(i).getAsJsonObject());
        }

        for (int i = 0; i < s_JsonObjects.size(); i++) {
            String jsonString = new String();
            jsonString = s_JsonObjects.get(i).toString();
            if (jsonString.contains(findText)) {
                result.add(s_JsonObjects.get(i));
            }
        }

        for (int i = 0; i < result.size(); i++) {
            String link = new newsObject(result.get(i)).getLink();
            links.add(link);
        }

        return links;
    }

}
