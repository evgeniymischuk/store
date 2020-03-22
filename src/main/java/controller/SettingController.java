package controller;

import db.Db;
import dto.ItemDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.function.Predicate;

import static db.CacheHelper.addToMemory;
import static db.CacheHelper.clearMemory;

@Controller
public class SettingController {
    private static String[] HEADERS = {"id", "title", "price", "description", "instagramLikeUrl"};

    public static void fillCacheFromCsv(Predicate<String> remove) throws IOException {
        clearMemory();
        Reader in = new FileReader("setting.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        for (CSVRecord record : records) {
            if (remove != null && remove.test(record.get("id"))) {
                continue;
            }
            final String id = record.get("id");
            final String title = record.get("title");
            final String price = record.get("price");
            final String description = record.get("description");
            final String instagramLikeUrl = record.get("instagramLikeUrl");
            addToMemory(
                    id,
                    title,
                    price,
                    description,
                    instagramLikeUrl
            );
        }
    }

    public static File save(String fileName, byte[] bytes) throws Exception {
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
            final HttpServletRequest request,
            File file
    ) throws IOException {
        final ServletOutputStream outputStream = response.getOutputStream();

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/force-download");
            response.setContentLength((int) file.length());
            String safariEncodedFileName = file.getName();
            String agent = request.getHeader("User-Agent");
            boolean isSafari = agent != null && agent.contains("Safari");
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

    @RequestMapping("/setting-admin-olya-solnceva")
    public String index(Model model) throws Exception {
        File f = new File("setting.csv");
        f.createNewFile();
        fillCacheFromCsv(null);
        model.addAttribute("itemList", Db.list);
        return "indexDisplayGridSetting";
    }

    @RequestMapping("/download")
    public void download(Model model,
                         @RequestParam("name") String name,
                         final HttpServletRequest request,
                         final HttpServletResponse response
    ) throws IOException {
        final File directory = new File("img");
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                if (file.getName().contains(name)) {
                    write(response, request, file);
                }
            }
        }
    }

    @RequestMapping("/setting-admin-olya-solnceva/add")
    @PostMapping
    public String addToCSVFile(
            ItemDto dto,
            @RequestParam(name = "pro-small-image") MultipartFile smallImage,
            @RequestParam(name = "pro-image") MultipartFile fullImage
    ) throws Exception {
        PrintWriter out = new PrintWriter("setting.csv");
        File savedSmallImage = save(dto.getId() + "_small.jpg", smallImage.getBytes());
        File savedFullImage = save(dto.getId() + ".jpg", fullImage.getBytes());
        if ((savedFullImage == null || !savedFullImage.exists()) || (savedSmallImage == null || !savedSmallImage.exists())) {
            return "redirect:/setting-admin-olya-solnceva";
        }
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for (ItemDto itemDto : Db.list) {
                printer.printRecord(
                        itemDto.getId(),
                        itemDto.getTitle(),
                        itemDto.getPrice(),
                        itemDto.getDescription(),
                        itemDto.getInstagramLikeUrl()
                );
            }
            printer.printRecord(
                    dto.getId(),
                    dto.getTitle(),
                    dto.getPrice(),
                    dto.getDescription(),
                    dto.getInstagramLikeUrl()
            );
        }
        return "redirect:/setting-admin-olya-solnceva";
    }

    @RequestMapping("/setting-admin-olya-solnceva/remove")
    @PostMapping
    public String removeFromCSVFile(ItemDto dto) throws IOException {
        fillCacheFromCsv(x -> x.equals(dto.getId()));
        try (CSVPrinter printer = new CSVPrinter(new PrintWriter("setting.csv"), CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for (ItemDto itemDto : Db.list) {
                printer.printRecord(
                        itemDto.getId(),
                        itemDto.getTitle(),
                        itemDto.getPrice(),
                        itemDto.getDescription(),
                        itemDto.getInstagramLikeUrl()
                );
            }
        }
        return "redirect:/setting-admin-olya-solnceva";
    }
}