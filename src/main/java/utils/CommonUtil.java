package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class CommonUtil {
    public static synchronized String getId() {
        return "uid_" + new Date().getTime();
    }

    public static synchronized String getDate() {
        return String.valueOf(new Date().getTime());
    }

    public static synchronized String join(List<String> list) {
        StringBuilder j = new StringBuilder();
        for (String id : list) {
            if (j.length() > 0) {
                j.append("zZ");
            }
            j.append(id);
        }
        return j.toString();
    }

    public static synchronized List<String> parse(String s) {
        if (s == null || s.isEmpty()) return new ArrayList<>();
        return Arrays.asList(s.split("zZ"));
    }
}
