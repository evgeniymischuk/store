package db;

import dto.ItemDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CacheHelper {

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

    public static void fillCacheFromCsv(List<String> list) throws IOException {
        clearMemory();
        final Reader in = new FileReader("admin_db.csv");
        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        for (final CSVRecord record : records) {
            if (list != null && list.contains(record.get("id"))) {
                continue;
            }
            final String id = record.get("id");
            final String title = record.get("title");
            final String price = record.get("price");
            final String description = record.get("description");
            final String instagramLikeUrl = record.get("instagramLikeUrl");
            final String reservation = record.get("reservation");
            addToMemory(
                    id,
                    title,
                    price,
                    description,
                    instagramLikeUrl,
                    reservation
            );
        }
    }

    private static String[] HEADERS = {"id", "title", "price", "description", "instagramLikeUrl", "reservation"};
}
