package huster.crawl.twitter;
import okhttp3.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.List;

public class FetchGitHubRepos {

    public static void main(String[] args) throws IOException {

        // Define the URL for fetching public repositories
        String url = "https://api.github.com/repositories?per_page=5";

        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

        // Build the request object
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Execute the request and get the response
        Response response = client.newCall(request).execute();

        // Check for successful response (200 OK)
        if (response.isSuccessful()) {

            // Get the response body as a string
            String responseBody = response.body().string();

            // Create a Gson instance
            Gson gson = new Gson();

            // Parse the JSON response using Gson
            // Define the type of the expected object (List of Repository)
            TypeToken<List<Repository>> typeToken = new TypeToken<List<Repository>>() {};
            List<Repository> repositories = gson.fromJson(responseBody, typeToken.getType());

            // Loop through each repository in the list
            for (Repository repo : repositories) {

                // Extract repository name and owner
                String name = repo.getName();
                String owner = repo.getOwner().getLogin();

                // Print repository information
                System.out.println("Name: " + name + ", Owner: " + owner);
            }

        } else {
            System.out.println("Request failed with code: " + response.code());
        }

        // Close the response body
        response.body().close();
    }

    // Define a class to represent a GitHub repository object
    static class Repository {
        private String name;
        private Owner owner;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }
    }

    // Define a class to represent the owner object
    static class Owner {
        private String login;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }
}
