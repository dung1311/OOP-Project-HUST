// package huster.crawl.twitter;
// import okhttp3.*;
// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;

// import java.io.IOException;

// public class GetTwitterUserInfo {
//     // Replace with actual Twitter API credentials
//     private static final String CONSUMER_KEY = "Cza5pFeYpXYp2QLtnX81uSReS";
//     private static final String CONSUMER_SECRET = "MlqwV4xVRPZg0WDZsBn7cTEET9ylYiKunuMB0kmBEJU6kQYuI3";
//     private static final String ACCESS_TOKEN = "1775864017585942528-6Cl4gqZSIH4LLmXbTi0FwFFkhypiE2";
//     private static final String ACCESS_TOKEN_SECRET = "MS4AfUbAGf0zzLHBt9Q6JVu0RfUHNXVAHmcNYsbZQy6zU";

//     private static final String TWITTER_API_URL = "https://api.twitter.com/1.1/users/show.json";
//     private static final String USER_ID = "1775864017585942528"; 

    
//     public static void main(String[] args) throws IOException {

//         // Define the search query (replace with your desired search term)
//         String query = "java";
    
//         // Base URL for Twitter search API
//         String url = "https://api.twitter.com/1.1/search/tweets.json?q=" + query;
    
//         // Create an OAuth helper for authorization (replace with your implementation)
//         String authorizationHeader = getAuthorizationHeader(url);
    
//         // Build the request object with authorization header
//         Request request = new Request.Builder().url(url).header("Authorization", authorizationHeader).build();
    
//         // Create an OkHttpClient instance
//         OkHttpClient client = new OkHttpClient();
    
//         // Execute the request and get the response
//         Response response = client.newCall(request).execute();
    
//         // Check for successful response (200 OK)
//         if (response.isSuccessful()) {
    
//             // Get the response body as a string
//             String responseBody = response.body().string();
    
//             // Parse the JSON response using Gson
//             Gson gson = new Gson();
//             TypeToken<SearchResponse> typeToken = new TypeToken<SearchResponse>() {};
//             searchResponse searchResponse = gson.fromJson(responseBody, typeToken.getType());
    
//             // Loop through each tweet in the statuses list
//             for (Status status : searchResponse.getStatuses()) {
    
//                 // Extract tweet text and user information
//                 String text = status.getText();
//                 String userName = status.getUser().getName();
//                 String screenName = status.getUser().getScreenName();
    
//                 // Print tweet information
//                 System.out.println("Text: " + text);
//                 System.out.println("User: " + userName + " @" + screenName);
//                 System.out.println("-------------------------");
//             }
    
//         } else {
//             System.out.println("Request failed with code: " + response.code());
//         }
    
//         // Close the response body
//         response.body().close();
//     }
        
        
// }
    



