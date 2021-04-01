import java.util.List;

public class WrapperFeatured {
    String message;
    FeaturedPlaylist playlists;

    @Override
    public String toString() {
        return playlists.toString();
    }

    public class FeaturedPlaylist {

        String href;
        List<PlaylistItem> items;
        int limit;
        String next;
        int offset;
        String previous;
        int total;

        @Override
        public String toString() {
            return items.toString();
        }

        public class PlaylistItem {
            boolean collaborative;
            String description;
            ExternalUrl external_urls;
            String href;
            String id;
            List<Image> images;
            String name;
            OwnerFeatured owner;
            String primary_color;
            String snapshot_id;
            Track tracks;
            String type;
            String uri;


            @Override
            public String toString() {
                return name + uri;

            }

            public class Track {
                String href;
                int total;
            }


            public class OwnerFeatured {
                String display_name;
                ExternalUrl external_urls;
                String href;
                String id;
                String type;
                String uri;
            }
        }
    }
}
