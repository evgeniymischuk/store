package db;

import dto.ItemDto;

public class CacheHelper {

    public static void addToMemory(String id, String title, String price, String description, String instagramLikeUrl) {
        ItemDto item = new ItemDto();

        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        item.setInstagramLikeUrl(instagramLikeUrl);
        item.setDescription(description);

        Db.list.add(item);
    }

    public static void clearMemory() {
        Db.list.clear();
    }
}
