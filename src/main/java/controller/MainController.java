package controller;

import db.CacheDb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

import static helpers.ItemHelper.ITEMS_CSV;
import static helpers.OrderHelper.ORDERS_CSV;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        final String userAgent = request.getHeader("User-Agent").toLowerCase();
        model.addAttribute("mobile", String.valueOf(userAgent.contains("android") || userAgent.contains("iphone")));
        model.addAttribute("itemList", CacheDb.itemList);
        return "index";
    }

    @RequestMapping("/clear-csv")
    public String clearCsv() {
        final File f = new File(ITEMS_CSV);
        final File f1 = new File(ORDERS_CSV);
        f.deleteOnExit();
        f1.deleteOnExit();
        return "redirect:/admin";
    }
}