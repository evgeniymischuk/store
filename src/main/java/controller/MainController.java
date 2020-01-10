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
    List<ItemDto> itemDtoList = new ArrayList<>();
    static {

    }
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("itemList", Db.list);

        return "index";
    }

    @RequestMapping("/info")
    public String info(Model model, @RequestParam Long id) {
        model.addAttribute("id", id);
        return "info";
    }
}