package NewsExample;

public class Main {
    public static void main(String[] args) {
    
        // ConnectionResponseParse response_parse = new ConnectionResponseParse();
        // response_parse.responseParse("https://en.wikipedia.org/wiki/Main_Page");
        // DocumentAndSelectTest  doc_sel = new DocumentAndSelectTest();
        //  doc_sel.selectFromDocument("https://www.blockchain.com/blog/");
        // List<String> test = doc_sel.getTopSearching("https://www.blockchain.com/blog/");
        // System.err.println(test.get(0));
        SaveToJSON save_json = new SaveToJSON();
        save_json.saveToJSON("https://www.blockchain.com/blog/");
    }
   
}


