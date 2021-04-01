import java.util.List;

public class WrapperPlaylists {
    Playlist playlists;

    @Override
    public String toString() {
        return "WrapperPlaylists{" +
                "href=" + playlists +
                '}';

    }

    public class Playlist {
        boolean collaborative;
        String description;
        Follower followers;
        ExternalUrl externalUrl;
        String href;
        String id;
        List<Image> images;
        String name;
        Owner owner;
        String snapshot_id;
        String tracks;
        String type;
        String uri;

        @Override
        public String toString() {
            return href + name;
        }

        public class Owner {
            ExternalUrl externalUrl;
            String href;
            String id;
            String type;
            String uri;

        }

        public class Follower {
            String href;
            int total;
        }


    }

}
