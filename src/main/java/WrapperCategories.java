import java.util.List;

public class WrapperCategories {
    Categories categories;

    @Override
    public String toString() {
        return categories.toString();
    }

    public class Categories {
        String href;
        List<CategoryItem> items;
        int limit;
        String next;
        int offset;
        String previous;
        int total;

        @Override
        public String toString() {
            return items.toString();
        }

        public class CategoryItem {
            String href;
            List<CategoryIcon> icons;
            String id;
            String name;

            @Override
            public String toString() {
                return id;

            }

            public class CategoryIcon {
                int height;
                String url;
                int width;

            }

        }

    }

}
