package controller;

import db.CacheDb;
import dto.ItemDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import static helpers.CommonHelper.getId;
import static helpers.FileHelper.save;
import static helpers.ItemHelper.*;
import static helpers.OrderHelper.ORDERS_CSV;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String admin(Model model) throws Exception {
        final File f = new File(ITEMS_CSV);
        final File f1 = new File(ORDERS_CSV);
        if (!f.exists() || !f1.exists()) {
            if (!f.createNewFile() || !f1.createNewFile()) {
                throw new Exception("doesnt create file");
            }
        }
        refreshCache();
        model.addAttribute("itemList", CacheDb.itemList);
        return "admin";
    }

    @RequestMapping("/admin/add")
    @PostMapping
    public String add(
            ItemDto dto,
            @RequestParam(name = "small-image") MultipartFile smallImage,
            @RequestParam(name = "full-image") MultipartFile fullImage
    ) throws Exception {
        dto.setId(getId());
        final File savedSmallImage = save(dto.getId() + "_small.jpg", smallImage.getBytes());
        final File savedFullImage = save(dto.getId() + ".jpg", fullImage.getBytes());

        if ((savedFullImage == null || !savedFullImage.exists()) || (savedSmallImage == null || !savedSmallImage.exists())) {
            return "redirect:/admin";
        }
        final CSVFormat csvFormat = CacheDb.itemList.size() == 0 ? CSVFormat.DEFAULT.withHeader(ITEM_HEADER) : CSVFormat.DEFAULT;
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(ITEMS_CSV, true), csvFormat)) {
            printer.printRecord(
                    dto.getId(),
                    dto.getTitle(),
                    dto.getPrice(),
                    dto.getDescription(),
                    dto.getInstagramLikeUrl(),
                    dto.getReservation(),
                    "false"
            );
        }

        return "redirect:/admin";
    }

    @RequestMapping("/admin/remove")
    @PostMapping
    public String remove(ItemDto dto) throws IOException {
        removeOrReservationFromCsv(Collections.singletonList(dto.getId()), false);
        return "redirect:/admin";
    }
}