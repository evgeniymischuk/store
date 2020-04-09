package utils;

import java.util.Date;

public abstract class CommonUtil {
    public static synchronized String getId() {
        return "uid_" + new Date().getTime();
    }
    public static synchronized String getDate() {
        return String.valueOf(new Date().getTime());
    }
}
