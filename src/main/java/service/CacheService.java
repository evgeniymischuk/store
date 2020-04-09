package service;

import db.CacheDb;
import dto.ItemDto;
import dto.OrderDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static service.ItemService.ITEMS_CSV;
import static service.ItemService.ITEM_HEADER;
import static service.OrderService.ORDERS_CSV;
import static service.OrderService.ORDER_HEADER;

public abstract class CacheService {

    public static void addItem(
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

    public static void addOrder(
            final String id,
            final String name,
            final String email,
            final String postal,
            final String status,
            final String done,
            final String number,
            final String track,
            final String info,
            final String purchasesIds,
            final String priceTotal,
            final String date
    ) {
        final OrderDto item = new OrderDto();

        item.setId(id);
        item.setName(name);
        item.setEmail(email);
        item.setPostal(postal);
        item.setStatus(status);
        item.setDone(done);
        item.setNumber(number);
        item.setTrack(track);
        item.setInfo(info);
        item.setPurchasesIds(Arrays.asList(purchasesIds.split("\\|")));
        item.setPriceTotal(priceTotal);
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

    public synchronized static void refreshCache() throws IOException {
        clearMemory();
        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(ITEM_HEADER)
                .withFirstRecordAsHeader()
                .parse(new FileReader(ITEMS_CSV));
        for (final CSVRecord record : records) {
            final String id = record.get("id");
            final String title = record.get("title");
            final String price = record.get("price");
            final String description = record.get("description");
            final String instagramLikeUrl = record.get("instagramLikeUrl");
            final String reservation = record.get("reservation");
            final String hide = record.get("hide");
            if (!"true".equalsIgnoreCase(hide)) {
                addItem(
                        id,
                        title,
                        price,
                        description,
                        instagramLikeUrl,
                        reservation
                );
            }
        }
        final Iterable<CSVRecord> recordsOrder = CSVFormat.DEFAULT
                .withHeader(ORDER_HEADER)
                .withFirstRecordAsHeader()
                .parse(new FileReader(ORDERS_CSV));
        for (final CSVRecord record : recordsOrder) {
            final String id = record.get("id");
            final String name = record.get("name");
            final String email = record.get("email");
            final String postal = record.get("postal");
            final String status = record.get("status");
            final String done = record.get("done");
            final String number = record.get("number");
            final String track = record.get("track");
            final String info = record.get("info");
            final String purchasesIds = record.get("purchasesIds");
            final String priceTotal = record.get("priceTotal");
            final String date = record.get("date");

            addOrder(
                    id,
                    name,
                    email,
                    postal,
                    status,
                    done,
                    number,
                    track,
                    info,
                    purchasesIds,
                    priceTotal,
                    date
            );
        }
    }
}
