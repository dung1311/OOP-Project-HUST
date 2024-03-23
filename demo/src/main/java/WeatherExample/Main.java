package WeatherExample;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.accuweather.com/en/browse-locations/asi/vn";
        Weather test = new Weather();
        test.weather(url);
    }
}
