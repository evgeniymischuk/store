package controller;

import db.Db;
import dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("itemList", Db.list);
        return "indexDisplayGrid";
    }

    @RequestMapping("/cards")
    public String cards(Model model) {
        model.addAttribute("itemList", Db.list);

        return "indexDisplayCards";
    }
}