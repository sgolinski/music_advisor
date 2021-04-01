
import com.google.gson.Gson;

public class SpotifyClient {

    public static final String REDIRECT_URI = "http://localhost:8080";
    public static final String RESPONSE_TYPE = "code";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String CLIENT_ID = "da39cc620bc54eb3ab43fc4bd78f02cf";
    public static final String CLIENT_SECRET = "73d0eb5e7379473d9ff2daee85fb956d";
    public static final String PATH = "https://accounts.spotify.com";
    public static final String LINK_NEW = "https://api.spotify.com/v1/browse/new-releases";
    public static final String LINK_FEATURES = "https://api.spotify.com/v1/browse/featured-playlists";
    public static final String LINK_CAT =  "https://api.spotify.com/v1/browse/categories";
    private String code;
    private String accessToken;
    private String jsonObj;


    public void setAccessToken() {
        Gson gson = new Gson();
        Wrapper token = gson.fromJson(this.getJsonObj(), Wrapper.class);
        this.accessToken = token.toString();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setJsonObj(String jsonObj) {
        this.jsonObj = jsonObj;
    }

    public String getJsonObj() {
        return jsonObj;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
