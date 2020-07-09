package db;

import dto.ItemDto;
import dto.OrderDto;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheDb {
    public volatile static List<ItemDto> itemList = new ArrayList<>();
    public volatile static Map<String, ItemDto> itemMap = new HashMap<>();
    public volatile static List<OrderDto> orderList = new ArrayList<>();
    public volatile static Map<String, OrderDto> orderMap = new HashMap<>();
    public volatile static Map<String, OrderDto> orderNumberMap = new HashMap<>();
    public volatile static Map<String, File> fileCacheMap = new HashMap<>();
}
