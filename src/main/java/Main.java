import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner sc = new Scanner(System.in);
        SpotifyServer server = new SpotifyServer(new SpotifyClient());
        while (true) {

            String action = sc.nextLine();
            String[] actions = action.split(" ");
            switch (actions[0]) {
                case "new":
                    System.out.println(desarializeNewReleased(server.request(SpotifyClient.LINK_NEW, actions[0])));
                    break;
                case "featured":
                    System.out.println(desarializeFetured(server.request(SpotifyClient.LINK_FEATURES, actions[0])));
                    break;
                case "categories":
                    System.out.println(deserializeCategories(server.request(SpotifyClient.LINK_CAT, actions[0])));
                    break;
                case "playlists":
                    System.out.println(deserializePlaylists(server.request("https://api.spotify.com/v1/browse/categories/" + actions[1] + "/playlists", actions[0])));
                    break;
                case "auth":
                    server.authorize();
                    server.getAccessToken();
                    break;
                case "exit":
                    System.exit(1);

            }
        }
    }


    private static WrapperCategories deserializeCategories(JsonObject json) {
        Gson gson = new Gson();
        WrapperCategories categories = gson.fromJson(json, WrapperCategories.class);
        return categories;
    }

    private static WrapperPlaylists deserializePlaylists(JsonObject json) {
        Gson gson = new Gson();
        WrapperPlaylists playlists = gson.fromJson(json, WrapperPlaylists.class);
        return playlists;
    }

    private static WrapperNewReleased desarializeNewReleased(JsonObject json) {
        Gson gson = new Gson();
        WrapperNewReleased newReleased = gson.fromJson(json, WrapperNewReleased.class);

        return newReleased;
    }

    private static WrapperFeatured desarializeFetured(JsonObject json) {
        Gson gson = new Gson();
        WrapperFeatured featured = gson.fromJson(json, WrapperFeatured.class);
        return featured;
    }
}