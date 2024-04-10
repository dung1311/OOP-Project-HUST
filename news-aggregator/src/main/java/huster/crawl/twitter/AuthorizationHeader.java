// import java.io.IOException;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.HashMap;
// import java.util.Map;
// import javax.crypto.Mac;
// import javax.crypto.spec.SecretKeySpec;

// private static String getAuthorizationHeader(String url) throws IOException {
//     // Replace with your credentials
//     String consumerKey = CONSUMER_KEY;
//     String consumerSecret = CONSUMER_SECRET;
//     String accessToken = ACCESS_TOKEN;
//     String accessTokenSecret = ACCESS_TOKEN_SECRET;

//     // OAuth parameters
//     Map<String, String> oauthParams = new HashMap<>();
//     oauthParams.put("oauth_consumer_key", consumerKey);
//     oauthParams.put("oauth_nonce", generateNonce()); // Implement to generate a random nonce
//     oauthParams.put("oauth_signature_method", "HMAC-SHA1");
//     oauthParams.put("oauth_timestamp", String.valueOf(System.currentTimeMillis() / 1000));
//     oauthParams.put("oauth_token", accessToken);
//     oauthParams.put("oauth_version", "1.0");

//     // Generate base string (URL and parameters)
//     String baseString = generateOAuthBaseString("GET", url, oauthParams);

//     // Generate signature
//     String signature = generateOAuthSignature(consumerSecret, accessTokenSecret, baseString);
//     oauthParams.put("oauth_signature", signature);

//     // Build authorization header
//     StringBuilder headerBuilder = new StringBuilder("Authorization: OAuth ");
//     for (Map.Entry<String, String> entry : oauthParams.entrySet()) {
//         headerBuilder.append(entry.getKey()).append("=\"").append(entry.getValue()).append("\", ");
//     }
//     headerBuilder.setLength(headerBuilder.length() - 2); // Remove trailing comma and space

//     // Create and execute HTTP request (example)
//     URL requestUrl = new URL(url);
//     HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
//     connection.setRequestMethod("GET");
//     connection.setRequestProperty("Authorization", headerBuilder.toString());
//     connection.connect();

//     // Process response (not shown)

//     return headerBuilder.toString(); // Return for demonstration purposes
// }

// private static String generateOAuthBaseString(String method, String url, Map<String, String> params) {
//     // Implement logic to build the base string as per OAuth spec
// }

// private static String generateOAuthSignature(String consumerSecret, String accessTokenSecret, String baseString) throws IOException {
//     String key = consumerSecret + "&" + accessTokenSecret;
//     SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
//     Mac mac = Mac.getInstance("HmacSHA1");
//     mac.init(signingKey);
//     byte[] bytes = mac.doFinal(baseString.getBytes("UTF-8"));
//     return new String(Base64Encoder.encode(bytes), "UTF-8");
// }

// private static String generateNonce() {
//     // Implement logic to generate a random string for the nonce parameter
// }
