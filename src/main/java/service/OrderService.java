package service;

import dto.OrderDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import static db.CacheDb.*;
import static org.apache.commons.csv.CSVFormat.DEFAULT;
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
        final CSVFormat csvFormat = orderMap.size() == 0 ? DEFAULT.withHeader(ORDER_HEADER) : DEFAULT;
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(ORDERS_CSV, true), csvFormat)) {
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
                    dto.getPurchasesIdsString(),
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

    public synchronized static void refresh(
            OrderDto orderDto
    ) throws IOException {
        final String idInner = orderDto.getId();
        if ((idInner == null || idInner.isEmpty())
                || (orderDto.getPurchasesIds() == null &&
                orderDto.getDate() == null &&
                orderDto.getDone() != null &&
                orderDto.getEmail() == null &&
                orderDto.getInfo() == null &&
                orderDto.getName() == null &&
                orderDto.getPostal() == null &&
                orderDto.getPriceTotal() == null &&
                orderDto.getNumber() == null
        )) return;
        final Reader in = new FileReader(ORDERS_CSV);
        final Iterable<CSVRecord> records = DEFAULT
                .withHeader(ORDER_HEADER)
                .withFirstRecordAsHeader()
                .parse(in);
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(ORDERS_CSV), DEFAULT.withHeader(ORDER_HEADER))) {
            for (final CSVRecord record : records) {
                final String id = record.get("id");
                String name = record.get("name");
                String email = record.get("email");
                String postal = record.get("postal");
                String status = record.get("status");
                String done = record.get("done");
                String number = record.get("number");
                String track = record.get("track");
                String info = record.get("info");
                String purchasesIds = record.get("purchasesIds");
                String priceTotal = record.get("priceTotal");
                String date = record.get("date");
                if (idInner.equalsIgnoreCase(id)) {
                    if (orderDto.getPurchasesIds() != null) {
                        purchasesIds = orderDto.getPurchasesIdsString();
                    }
                    if (orderDto.getDate() != null) {
                        date = orderDto.getDate();
                    }
                    if (orderDto.getDone() != null) {
                        done = orderDto.getDone();
                    }
                    if (orderDto.getEmail() != null) {
                        email = orderDto.getEmail();
                    }
                    if (orderDto.getInfo() != null) {
                        info = orderDto.getInfo();
                    }
                    if (orderDto.getName() != null) {
                        name = orderDto.getName();
                    }
                    if (orderDto.getPostal() != null) {
                        postal = orderDto.getPostal();
                    }
                    if (orderDto.getPriceTotal() != null) {
                        priceTotal = orderDto.getPriceTotal();
                    }
                    if (orderDto.getStatus() != null) {
                        status = orderDto.getStatus();
                    }
                    if (orderDto.getNumber() != null) {
                        number = orderDto.getNumber();
                    }
                    CacheService.addOrder(
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
                printer.printRecord(
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
}
