package helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import static helpers.CacheHelper.addItemToMemory;
import static helpers.CacheHelper.clearMemory;

public abstract class ItemHelper {
    public static final String ITEMS_CSV = "items.csv";
    public static String[] ITEM_HEADER = {"id", "title", "price", "description", "instagramLikeUrl", "reservation", "hide"};

    public synchronized static void removeOrReservationFromCsv(
            final List<String> ids, boolean isReservation
    ) throws IOException {
        if (ids == null || ids.isEmpty()) return;
        final Reader in = new FileReader(ITEMS_CSV);
        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(ITEM_HEADER)
                .withFirstRecordAsHeader()
                .parse(in);
        try (CSVPrinter printer = new CSVPrinter(new PrintWriter(ITEMS_CSV), CSVFormat.DEFAULT.withHeader(ITEM_HEADER))) {
            for (final CSVRecord record : records) {
                final String id = record.get("id");
                final String title = record.get("title");
                final String price = record.get("price");
                final String description = record.get("description");
                final String instagramLikeUrl = record.get("instagramLikeUrl");
                String reservation = record.get("reservation");
                String hide = record.get("hide");
                if (ids.contains(id)) {
                    if (isReservation) {
                        reservation = "true";
                    }
                    hide = "true";
                }
                printer.printRecord(
                        id,
                        title,
                        price,
                        description,
                        instagramLikeUrl,
                        reservation,
                        hide
                );
            }
        }
        refreshCache();
    }

    public synchronized static void refreshCache() throws IOException {
        clearMemory();
        final Reader in = new FileReader(ITEMS_CSV);
        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(ITEM_HEADER)
                .withFirstRecordAsHeader()
                .parse(in);
        for (final CSVRecord record : records) {
            final String id = record.get("id");
            final String title = record.get("title");
            final String price = record.get("price");
            final String description = record.get("description");
            final String instagramLikeUrl = record.get("instagramLikeUrl");
            final String reservation = record.get("reservation");
            final String hide = record.get("hide");
            if (!"true".equalsIgnoreCase(hide)) {
                addItemToMemory(
                        id,
                        title,
                        price,
                        description,
                        instagramLikeUrl,
                        reservation
                );
            }
        }
    }
}
