package huster.crawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONObject;

 class TestPython {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Create the command to execute the Python script
        String[] command = {"python", "DrawTable.py", "abc"}; // Replace "sample_tweets" with your actual filename

        // Execute the Python script using subprocess
        Process process = Runtime.getRuntime().exec(command);

        // Read the output from the Python script
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder(); // to store the entire JSON output

        while ((line = reader.readLine()) != null) {
            output.append(line); // Append each line of output
        }

        // Wait for the Python script to finish executing
        process.waitFor();

        // Check the exit code of the Python script
        if (process.exitValue() != 0) {
            System.err.println("Error running DrawTable.py");
        } else {
            // Parse the JSON output
            String jsonString = output.toString();
            JSONArray jsonArray = new JSONArray(jsonString);

            // Process the parsed JSON data (e.g., display or analyze)
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject tweetData = jsonArray.getJSONObject(i);
                String time = tweetData.getString("time");
                int comments = tweetData.getInt("comment");
                int retweets = tweetData.getInt("retweet");
                int quotes = tweetData.getInt("quote");
                int likes = tweetData.getInt("like");

                // Process individual tweet data as needed
                System.out.println("Time: " + time + ", Comments: " + comments + ", Retweets: " + retweets + ", Quotes: " + quotes + ", Likes: " + likes);
            }
        }
    }
}



