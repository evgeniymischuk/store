package helpers;

import dto.OrderDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import static helpers.CommonHelper.getId;

public abstract class OrderHelper {
    public static final String ORDERS_CSV = "orders.csv";
    public static String[] ORDER_HEADER = {"id", "status", "done", "track", "info", "purchasesIds", "price", "date"};

    public synchronized static void addToOrderCsv(
            OrderDto dto
    ) throws IOException {
        if (dto == null || dto.getPurchasesIds() == null) throw new IOException("orderDto is empty");
        final Reader in = new FileReader(ORDERS_CSV);
        dto.setId(getId());
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(ORDERS_CSV, true), CSVFormat.DEFAULT.withHeader(ORDER_HEADER))) {
            printer.printRecord(
                    dto.getId(),
                    dto.getStatus(),
                    dto.getDone(),
                    dto.getTrack(),
                    dto.getInfo(),
                    dto.getPurchasesIds(),
                    dto.getPrice(),
                    dto.getDate()
            );
        }
    }
}
