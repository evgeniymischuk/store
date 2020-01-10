package db;

import dto.ItemDto;

public class DbHelper {

    public static void addToDb(String id, String name, String price) {
        ItemDto item = new ItemDto();

        item.setId(id);
        item.setPrice(name);
        item.setName(price);

        Db.list.add(item);
    }
}
