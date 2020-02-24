package db;

import dto.ItemDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static controller.SettingController.fillCacheFromCsv;

//emulateDb
public class Db {

    public volatile static List<ItemDto> list = new LinkedList<>();

    static {
        try {
            fillCacheFromCsv(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    static {
//        addToMemory("item_1",
//                "Кольцо Космос",
//                "4000",
//                "Кольцо  Mountain river  изготовлено из ореха и ювелирной смолы. Уникальный горный пейзаж полностью выполнен из дерева. Маленький мир у Вас на руке!",
//                "https://www.instagram.com/p/B7lXf5hKpLe/"
//        );
//
//        addToMemory(
//                "item_2",
//                "Кольцо Лес",
//                "4100",
//                "Кольцо  Mountain river  изготовлено из ореха и ювелирной смолы. Уникальный горный пейзаж полностью выполнен из дерева. Маленький мир у Вас на руке!",
//                "https://www.instagram.com/p/B4QE-_JqdVV/"
//        );
//
//        addToMemory(
//                "item_3",
//                "Кольцо Горы",
//                "7000",
//                "Кольцо  Mountain river  изготовлено из ореха и ювелирной смолы. Уникальный горный пейзаж полностью выполнен из дерева. Маленький мир у Вас на руке!",
//                "https://www.instagram.com/p/B81WOclKbal/"
//        );
//
//        addToMemory(
//                "item_4",
//                "Серьги Лед",
//                "7000",
//                "Кольцо  Mountain river  изготовлено из ореха и ювелирной смолы. Уникальный горный пейзаж полностью выполнен из дерева. Маленький мир у Вас на руке!",
//                "https://www.instagram.com/p/B6I94bmKrZU/"
//        );
//
//        addToMemory("item_5",
//                "Кольцо Снежная гора",
//                "4100",
//                "Кольцо  Mountain river  изготовлено из ореха и ювелирной смолы. Уникальный горный пейзаж полностью выполнен из дерева. Маленький мир у Вас на руке!",
//                "https://www.instagram.com/p/B5DWPqpKe1k/"
//        );
//
//        addToMemory("item_6",
//                "Кольцо Снежный лес",
//                "7000",
//                "Кольцо  Mountain river  изготовлено из ореха и ювелирной смолы. Уникальный горный пейзаж полностью выполнен из дерева. Маленький мир у Вас на руке!",
//                "https://www.instagram.com/p/B7lXf5hKpLe/"
//        );
//    }
}
