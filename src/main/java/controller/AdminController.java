package controller;

import db.CacheDb;
import dto.ItemDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collections;
import java.util.List;

import static db.CacheHelper.fillCacheFromCsv;
import static org.springframework.security.config.Elements.HEADERS;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String index(Model model) throws Exception {
        final File f = new File("admin_db.csv");
        final boolean newFile = f.createNewFile();
        model.addAttribute("itemList", CacheDb.list);
        return "indexDisplayGridSetting";
    }

    @RequestMapping("/download")
    public void download(Model model,
                         @RequestParam("name") String name,
                         final HttpServletResponse response
    ) throws IOException {
        if (StringUtils.isEmpty(name)) return;
        final File directory = new File("img");
        if (directory.exists()) {
            final File[] fileList = directory.listFiles();
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.getName().contains(name)) {
                        write(response, file);
                    }
                }
            }
        }
    }

    @RequestMapping("/admin/add")
    @PostMapping
    public String addToCSVFile(
            ItemDto dto,
            @RequestParam(name = "small-image") MultipartFile smallImage,
            @RequestParam(name = "full-image") MultipartFile fullImage
    ) throws Exception {
        final PrintWriter out = new PrintWriter("admin_db.csv");
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

    private void removeFromCsv(final List<String> id) throws IOException {
        fillCacheFromCsv(id);
        try (CSVPrinter printer = new CSVPrinter(new PrintWriter("admin_db.csv"), CSVFormat.DEFAULT.withHeader(HEADERS))) {
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
        }
    }

    public static File save(String fileName, byte[] bytes) {
        OutputStream opStream = null;
        File f = null;
        try {
            f = new File("img");
            boolean b = !f.exists() && f.mkdir() || f.exists();
            if (!b) {
                throw new IOException("no create img");
            }
            f = new File("img/" + fileName);
            boolean exist = f.exists();
            if (!exist && f.createNewFile()) {
                opStream = new FileOutputStream(f);
                opStream.write(bytes);
                opStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (opStream != null) opStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return f;
    }

    private static void write(
            final HttpServletResponse response,
            File file
    ) throws IOException {
        final ServletOutputStream outputStream = response.getOutputStream();

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/force-download");
            response.setContentLength((int) file.length());
            String safariEncodedFileName = file.getName();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + safariEncodedFileName + "\"; filename*=UTF-8''" + safariEncodedFileName);
            InputStream in = new FileInputStream(file);
            byte[] readBuffer = new byte[(int) file.length()];
            int length;
            while ((length = in.read(readBuffer)) != -1) {
                outputStream.write(readBuffer, 0, length);
            }
        } catch (Exception ignored) {
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}