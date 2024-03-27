package website.fromCoinDesk;

public class Main {
    public static void main(String[] args) {
        // Resources test1 = new Resources();
        // List<String> test =  test1.getLinks("https://www.coindesk.com/");
        // for(int i = 0; i < test.size(); i++)
        // {
        //     System.out.println(test.get(i));
        // }
            String url = "https://www.coindesk.com/";

        WriteOnJsonFile file = new WriteOnJsonFile();
        file.writeOnJsonFile(url);

    }
}
