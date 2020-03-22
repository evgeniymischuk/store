package db;

import dto.ItemDto;

import java.io.IOException;
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
}
