package db;

import dto.ItemDto;
import dto.OrderDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static service.CacheService.refreshCache;

public abstract class CacheDb {

    public volatile static List<ItemDto> itemList = new ArrayList<>();
    public volatile static Map<String, ItemDto> itemMap = new HashMap<>();
    public volatile static List<OrderDto> orderList = new ArrayList<>();
    public volatile static Map<String, OrderDto> orderMap = new HashMap<>();

    static {
        try {
            refreshCache();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
