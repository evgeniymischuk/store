package helpers;

import db.CacheDb;
import dto.ItemDto;

public abstract class CacheHelper {

    public static void addToMemory(final String id,
                                   final String title,
                                   final String price,
                                   final String description,
                                   final String instagramLikeUrl,
                                   final String reservation) {
        final ItemDto item = new ItemDto();

        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        item.setInstagramLikeUrl(instagramLikeUrl);
        item.setDescription(description);
        item.setReservation(reservation);

        CacheDb.list.add(item);
    }

    public static void clearMemory() {
        CacheDb.list.clear();
    }
}
