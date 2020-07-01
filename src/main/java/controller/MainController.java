package controller;

import db.CacheDb;
import dto.ItemDto;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import static service.CacheService.refreshCache;
import static service.ItemService.ITEMS_CSV;
import static service.ItemService.findById;
import static service.OrderService.ORDERS_CSV;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        final String userAgent = request.getHeader("User-Agent").toLowerCase();
        model.addAttribute("mobile", String.valueOf(userAgent.contains("android") || userAgent.contains("iphone")));
        model.addAttribute("itemList", CacheDb.itemList);
        return "index";
    }

    @RequestMapping("/archive")
    public String archive(Model model, @RequestParam String id, HttpServletRequest request) throws IOException {
        final String userAgent = request.getHeader("User-Agent").toLowerCase();
        model.addAttribute("mobile", String.valueOf(userAgent.contains("android") || userAgent.contains("iphone")));
        model.addAttribute("itemList", findById(Collections.singletonList(id)));
        return "archive";
    }

    @RequestMapping("/clear-csv")
    public String clearCsv() {
        final File f = new File(ITEMS_CSV);
        final File f1 = new File(ORDERS_CSV);
        f.deleteOnExit();
        f1.deleteOnExit();
        return "redirect:/admin";
    }

    @PostConstruct
    public void post() throws Exception {
        final File i = new File(ITEMS_CSV);
        final File o = new File(ORDERS_CSV);
        if (!i.exists()) {
            if (!i.createNewFile()) {
                throw new Exception("doesnt create file");
            }
        }
        if (!o.exists()) {
            if (!o.createNewFile()) {
                throw new Exception("doesnt create file");
            }
        }
        CacheDb regInMemoryStaticFields = new CacheDb();
        refreshCache();
    }
}