package service;

import db.CacheDb;
import dto.OrderDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.buf.StringUtils;

import java.io.FileWriter;
import java.io.IOException;

import static utils.CommonUtil.getDate;
import static utils.CommonUtil.getId;

public abstract class OrderService {
    public static final String ORDERS_CSV = "orders.csv";
    public static String[] ORDER_HEADER = {"id", "status", "done", "track", "info", "purchasesIds", "priceTotal", "date"};

    public synchronized static String addAndGetId(
            OrderDto dto
    ) throws IOException {
        if (dto == null || dto.getPurchasesIds() == null) throw new IOException("orderDto is empty");
        dto.setId(getId());
        dto.setStatus("Проверка оплаты");
        dto.setDate(getDate());
        dto.setNumber(String.valueOf(CacheDb.orderMap.size() + 1));
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(ORDERS_CSV, true), CSVFormat.DEFAULT.withHeader(ORDER_HEADER))) {
            printer.printRecord(
                    dto.getId(),
                    dto.getName(),
                    dto.getEmail(),
                    dto.getPostal(),
                    dto.getStatus(),
                    dto.getDone(),
                    dto.getNumber(),
                    dto.getTrack(),
                    dto.getInfo(),
                    StringUtils.join(dto.getPurchasesIds(), '|'),
                    dto.getPriceTotal(),
                    dto.getDate()
            );
        }

        CacheDb.orderList.add(dto);
        CacheDb.orderMap.put(dto.getId(), dto);

        return dto.getId();
    }
}
