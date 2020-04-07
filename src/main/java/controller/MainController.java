package controller;

import db.CacheDb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        final String userAgent = request.getHeader("User-Agent").toLowerCase();
        model.addAttribute("mobile", String.valueOf(userAgent.contains("android") || userAgent.contains("iphone")));
        model.addAttribute("itemList", CacheDb.list);
        return "indexDisplayGrid";
    }
}