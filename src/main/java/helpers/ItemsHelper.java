package helpers;

import db.CacheDb;
import dto.ItemDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import static helpers.CacheHelper.addToMemory;
import static helpers.CacheHelper.clearMemory;

public abstract class ItemsHelper {
    public synchronized static void removeOrReservationFromCsv(
            final List<String> ids,
            final boolean isReservation
    ) throws IOException {
        if (ids == null || ids.isEmpty()) return;
        fillCacheFromCsv(ids, isReservation);
        try (CSVPrinter printer = new CSVPrinter(new PrintWriter("items.csv"), CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for (ItemDto itemDto : CacheDb.list) {
                printer.printRecord(
                        itemDto.getId(),
                        itemDto.getTitle(),
                        itemDto.getPrice(),
                        itemDto.getDescription(),
                        itemDto.getInstagramLikeUrl(),
                        itemDto.getReservation()
                );
            }
        }
    }

    public synchronized static void fillCacheFromCsv(List<String> list, boolean isReservation) throws IOException {
        clearMemory();
        final Reader in = new FileReader("items.csv");
        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        for (final CSVRecord record : records) {
            String reservation = record.get("reservation");
            if (list != null && list.contains(record.get("id"))) {
                if (isReservation) {
                    reservation = "true";
                } else {
                    continue;
                }
            }
            final String id = record.get("id");
            final String title = record.get("title");
            final String price = record.get("price");
            final String description = record.get("description");
            final String instagramLikeUrl = record.get("instagramLikeUrl");
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

    public static String[] HEADERS = {"id", "title", "price", "description", "instagramLikeUrl", "reservation"};
}
