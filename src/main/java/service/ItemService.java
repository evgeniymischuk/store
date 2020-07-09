package service;

import dto.ItemDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static db.CacheDb.itemList;
import static db.CacheDb.itemMap;
import static service.CacheService.addItem;

public abstract class ItemService {
    public static final String ITEMS_CSV = "items.csv";
    public static String[] ITEM_HEADER = {"id", "title", "price", "description", "additionalItem", "reservation", "hide"};

    public synchronized static void removeOrReservation(
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
                final String additionalItem = record.get("additionalItem");
                String reservation = record.get("reservation");
                String hide = record.get("hide");
                if (ids.contains(id)) {
                    if (isReservation) {
                        reservation = "true";
                        ItemDto itemDto = itemMap.get(id);
                        itemDto.setReservation(reservation);
                    } else {
                        hide = "true";
                        itemMap.remove(id);
                        itemList = new ArrayList<>(itemMap.values());
                    }
                }
                printer.printRecord(
                        id,
                        title,
                        price,
                        description,
                        additionalItem,
                        reservation,
                        hide
                );
            }
        }
    }

    public synchronized static void refresh(
            ItemDto itemDto
    ) throws IOException {
        final String idInner = itemDto.getId();
        if ((idInner == null || idInner.isEmpty()) || (
                itemDto.getDescription() == null &&
                        itemDto.getAdditionalItem() == null &&
                        itemDto.getPrice() == null &&
                        itemDto.getTitle() == null)) {
            return;
        }
        final Reader in = new FileReader(ITEMS_CSV);
        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(ITEM_HEADER)
                .withFirstRecordAsHeader()
                .parse(in);
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(ITEMS_CSV), CSVFormat.DEFAULT.withHeader(ITEM_HEADER))) {
            for (final CSVRecord record : records) {
                final String id = record.get("id");
                String title = record.get("title");
                String price = record.get("price");
                String description = record.get("description");
                String additionalItem = record.get("additionalItem");
                String reservation = record.get("reservation");
                String hide = record.get("hide");
                if (idInner.equalsIgnoreCase(id)) {
                    if (itemDto.getDescription() != null) {
                        description = itemDto.getDescription();
                    }
                    if (itemDto.getHide() != null) {
                        hide = itemDto.getHide();
                    }
                    if (itemDto.getAdditionalItem() != null) {
                        additionalItem = itemDto.getAdditionalItem();
                    }
                    if (itemDto.getPrice() != null) {
                        price = itemDto.getPrice();
                    }
                    if (itemDto.getReservation() != null) {
                        reservation = itemDto.getReservation();
                    }
                    if (itemDto.getTitle() != null) {
                        title = itemDto.getTitle();
                    }
                    addItem(
                            id,
                            title,
                            price,
                            description,
                            additionalItem,
                            reservation
                    );
                }
                printer.printRecord(
                        id,
                        title,
                        price,
                        description,
                        additionalItem,
                        reservation,
                        hide
                );
            }
        }
    }

    public synchronized static List<ItemDto> findById(
            final List<String> ids
    ) throws IOException {
        if (ids == null || ids.isEmpty()) new ArrayList<>();
        List<ItemDto> list = new ArrayList<>();
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
            final String additionalItem = record.get("additionalItem");
            String reservation = record.get("reservation");
            String hide = record.get("hide");
            ItemDto i = new ItemDto();
            i.setId(id);
            i.setTitle(title);
            i.setPrice(price);
            i.setDescription(description);
            i.setAdditionalItem(additionalItem);
            i.setReservation(reservation);
            i.setHide(hide);
            if (ids.contains(id)) {
                list.add(i);
            }
        }

        return list;
    }
}
