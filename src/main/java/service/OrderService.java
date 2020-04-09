package service;

import dto.OrderDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

import static db.CacheDb.*;
import static utils.CommonUtil.getDate;
import static utils.CommonUtil.getId;

public abstract class OrderService {
    public static final String ORDERS_CSV = "orders.csv";
    public static String[] ORDER_HEADER = {"id", "name", "email", "postal", "status", "done", "number", "track", "info", "purchasesIds", "priceTotal", "date"};

    public synchronized static String addAndGetId(
            OrderDto dto
    ) throws IOException {
        if (dto == null || dto.getPurchasesIds() == null) throw new IOException("orderDto is empty");
        dto.setId(getId());
        dto.setStatus("Проверка оплаты");
        dto.setDate(getDate());
        dto.setNumber(String.valueOf(orderMap.size() + 1));
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(ORDERS_CSV, true), CSVFormat.DEFAULT.withHeader(ORDER_HEADER))) {
            StringBuilder purchasesIds = new StringBuilder();
            for (String id : dto.getPurchasesIds()) {
                if (purchasesIds.length() > 0) {
                    purchasesIds.append("zZ");
                }
                purchasesIds.append(id);
            }
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
                    purchasesIds,
                    dto.getPriceTotal(),
                    dto.getDate()
            );
        }
        for (final String uid : dto.getPurchasesIds()) {
            dto.getPurchasesDtoList().add(itemMap.get(uid));
        }
        orderList.add(dto);
        orderMap.put(dto.getId(), dto);
        orderNumberMap.put(dto.getNumber(), dto);

        return dto.getId();
    }
}
