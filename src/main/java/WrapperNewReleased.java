import java.util.List;

public class WrapperNewReleased {
    Albums albums;

    @Override
    public String toString() {
        return albums.toString();
    }

    public class Albums {
        String href;
        List<AlbumItem> items;
        int limit;
        String next;
        int offset;
        String previous;
        int total;

        @Override
        public String toString() {
            return items.toString();

        }

        public class AlbumItem {
            String album_type;
            List<Artist> artists;
            String[] available_markets;
            ExternalUrl external_urls;
            String href;
            String id;
            List<Image> images;
            String name;
            String type;
            String uri;

            @Override
            public String toString() {

                return name + artists.toString() + href + "/n";
            }

            public class Artist {
                String id;
                ExternalUrl external_urls;
                String href;
                String name;
                String type;
                String uri;

                @Override
                public String toString() {
                    return name;
                }
            }

        }
    }

}
