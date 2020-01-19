package db;

import dto.ItemDto;

public class DbHelper {

    public static void addToDb(String id, String name, String price) {
        ItemDto item = new ItemDto();

        item.setId(id);
        item.setName(name);
        item.setPrice(price);

        Db.list.add(item);
    }
}
