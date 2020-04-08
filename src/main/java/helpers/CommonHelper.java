package helpers;

import java.util.Date;

public abstract class CommonHelper {
    public static synchronized String getId() {
        return "uid_" + new Date().getTime();
    }
}
