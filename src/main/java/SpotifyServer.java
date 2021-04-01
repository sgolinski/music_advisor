import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SpotifyServer {

    SpotifyClient spotifyClient;

    SpotifyServer(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    public void authorize() throws IOException, InterruptedException {

        System.out.println("use this link to request the access code");
        System.out.println(SpotifyClient.PATH + "/authorize?client_id=" + SpotifyClient.CLIENT_ID + "&redirect_uri=" + SpotifyClient.REDIRECT_URI + "&response_type=" + SpotifyClient.RESPONSE_TYPE);
        HttpServer server = HttpServer.create();

        server.bind(new InetSocketAddress(8080), 0);
        server.start();
        System.out.println("waiting for code...");

        server.createContext("/", exchange -> {
                    String query = exchange.getRequestURI().getQuery();
                    String response;
                    if (query != null && query.contains("code")) {
                        spotifyClient.setCode(query.substring(5));
                        response = "Got the code. Return back to your program.";
                    } else {
                        response = "Authorization code not found. Try again.";
                    }
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }
        );

        while (spotifyClient.getCode() == null) {
            Thread.sleep(100);
        }
        server.stop(1);
        Thread.sleep(500);
    }

    public void getAccessToken() throws IOException, InterruptedException {

        System.out.println("code received");
        System.out.println("making http request for access_token...");

        HttpClient client = HttpClient.newBuilder().build();
        System.out.println(client.proxy());

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(SpotifyClient.PATH + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("client_id=" + SpotifyClient.CLIENT_ID + "&client_secret=" + SpotifyClient.CLIENT_SECRET
                        + "&grant_type=" + SpotifyClient.GRANT_TYPE + "&code=" + spotifyClient.getCode() + "&redirect_uri=" + SpotifyClient.REDIRECT_URI))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response:");
        System.out.println(response.body());

        spotifyClient.setJsonObj(response.body());
        spotifyClient.setAccessToken();
        System.out.println("---SUCCESS---");

    }

    public JsonObject request(String uri, String actionName) throws IOException, InterruptedException {


        HttpClient client = HttpClient.newBuilder().build();
        System.out.println(client.proxy());
        System.out.println(spotifyClient.getAccessToken());
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + spotifyClient.getAccessToken())
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("response:");
        JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
        System.out.println(jo);
        return jo;

    }

}
