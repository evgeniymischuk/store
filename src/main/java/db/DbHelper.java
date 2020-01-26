package db;

import dto.ItemDto;

public class DbHelper {

    public static void addToDb(String id, String name, String price, String description) {
        ItemDto item = new ItemDto();

        item.setId(id);
        item.setName(name);
        item.setPrice(price);
        item.setLikeCount("999");
        item.setDescription(description);

        Db.list.add(item);
    }
}
