package helpers;

import db.CacheDb;
import dto.ItemDto;
import dto.OrderDto;

import java.util.List;

public abstract class CacheHelper {

    public static void addItemToMemory(
            final String id,
            final String title,
            final String price,
            final String description,
            final String instagramLikeUrl,
            final String reservation
    ) {
        final ItemDto item = new ItemDto();

        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        item.setInstagramLikeUrl(instagramLikeUrl);
        item.setDescription(description);
        item.setReservation(reservation);

        CacheDb.itemList.add(item);
        CacheDb.itemMap.put(id, item);
    }

    public static void addOrderToMemory(
            final String id,
            final String price,
            final List<String> purchasesIds,
            final String info,
            final String status,
            final String done,
            final String track,
            final String date
    ) {
        final OrderDto item = new OrderDto();

        item.setId(id);
        item.setPrice(price);
        item.setPurchasesIds(purchasesIds);
        item.setInfo(info);
        item.setStatus(status);
        item.setDone(done);
        item.setTrack(track);
        item.setDate(date);

        CacheDb.orderList.add(item);
        CacheDb.orderMap.put(id, item);
    }

    public static void clearMemory() {
        CacheDb.itemList.clear();
        CacheDb.itemMap.clear();
        CacheDb.orderList.clear();
        CacheDb.orderMap.clear();
    }
}
