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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import static db.CacheHelper.HEADERS;
import static db.CacheHelper.fillCacheFromCsv;
import static db.Helper.removeFromCsv;
import static db.Helper.save;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String index(Model model) throws Exception {
        final File f = new File("items.csv");
        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new Exception("doesnt create file");
            }
        }
        fillCacheFromCsv(null);
        model.addAttribute("itemList", CacheDb.list);
        return "admin";
    }

    @RequestMapping("/admin/add")
    @PostMapping
    public String addToCSVFile(
            ItemDto dto,
            @RequestParam(name = "small-image") MultipartFile smallImage,
            @RequestParam(name = "full-image") MultipartFile fullImage
    ) throws Exception {
        final PrintWriter out = new PrintWriter("items.csv");
        final File savedSmallImage = save(dto.getId() + "_small.jpg", smallImage.getBytes());
        final File savedFullImage = save(dto.getId() + ".jpg", fullImage.getBytes());

        if ((savedFullImage == null || !savedFullImage.exists()) || (savedSmallImage == null || !savedSmallImage.exists())) {
            return "redirect:/admin";
        }
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for (ItemDto itemDto : CacheDb.list) {
                printer.printRecord(
                        itemDto.getId(),
                        itemDto.getTitle(),
                        itemDto.getPrice(),
                        itemDto.getDescription(),
                        itemDto.getInstagramLikeUrl(),
                        itemDto.getReservation()
                );
            }
            printer.printRecord(
                    dto.getId(),
                    dto.getTitle(),
                    dto.getPrice(),
                    dto.getDescription(),
                    dto.getInstagramLikeUrl(),
                    dto.getReservation()
            );
        }

        return "redirect:/admin";
    }

    @RequestMapping("/admin/remove")
    @PostMapping
    public String removeFromCSVFile(ItemDto dto) throws IOException {
        removeFromCsv(Collections.singletonList(dto.getId()));
        return "redirect:/admin";
    }
}