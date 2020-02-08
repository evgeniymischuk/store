package controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Controller
public class CloudController {

    @RequestMapping("/cloud")
    public String cloud(Model model) {
        return "cloudMain";
    }

    @RequestMapping("/cloud/download")
    public void download(Model model,
                         @RequestParam("dir") String dir,
                         @RequestParam("name") String name,
                         final HttpServletResponse response
    ) throws IOException {
        File directory = new File("cloud/" + dir);
        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.getName().equalsIgnoreCase(name)) {
                    write(response, file);
                }
            }
        }
    }

    @RequestMapping(value = "/cloud/{dir}")
    public String watch(Model model, @PathVariable("dir") String dir) throws IOException {
        List<String> images = new LinkedList<>();
        List<String> names = new LinkedList<>();
        File directory = new File("cloud/" + dir);
        if (directory.exists()) {
            final File[] files = directory.listFiles();
            for (final File file : files) {
                if (images.size() < 5) {
                    images.add("data:image/jpeg;base64," + new String(Base64.getEncoder().encode(readFileToByteArray(file)), StandardCharsets.UTF_8));
                }
                names.add(file.getName());
            }
        }
        model.addAttribute("images", images);
        model.addAttribute("names", names);

        return "cloudWatch";
    }

    @RequestMapping(value = "/cloud/watchCarousel/{dir}")
    public String watchCarousel(Model model, @PathVariable("dir") String dir) throws IOException {
        List<String> images = new LinkedList<>();
        List<String> names = new LinkedList<>();
        File directory = new File("cloud/" + dir);
        if (directory.exists()) {
            final File[] files = directory.listFiles();
            final int last = files.length - 1;
            final String lastName = files[last].getName();
            for (final File file : files) {
                if (images.size() < 4 || lastName.equals(file.getName())) {
                    images.add("data:image/jpeg;base64," + new String(Base64.getEncoder().encode(readFileToByteArray(file)), StandardCharsets.UTF_8));
                }
                names.add(file.getName());
            }
            model.addAttribute("last", last);
            model.addAttribute("lastImageInArray", Math.min(last, 4));
        }
        model.addAttribute("images", images);
        model.addAttribute("names", names);

        return "cloudWatchCarousel";
    }

    @RequestMapping(value = "/cloud/load/image", method = RequestMethod.GET)
    @ResponseBody
    public String loadImage(Model model,
                            @RequestParam("dir") String dir,
                            @RequestParam("name") String name
    ) {
        List<String> images = new LinkedList<>();
        File directory = new File("cloud/" + dir);
        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (images.size() < 1 && file.getName().equalsIgnoreCase(name)) {
                    images.add("data:image/jpeg;base64," + new String(Base64.getEncoder().encode(readFileToByteArray(file)), StandardCharsets.UTF_8));
                }
            }
        }
        return images.get(0);
    }

    @RequestMapping("/cloud/add")
    @PostMapping
    public String cloudAdd(Model model, @RequestParam(name = "pro-image") List<MultipartFile> images,
                           @RequestParam(name = "dir") String dir) throws Exception {
        String dir64 = new String(Base64.getEncoder().encode(dir.getBytes()), StandardCharsets.UTF_8)
                .replaceAll("/", "").replaceAll("[-+.^:,]", "");
        for (MultipartFile multipartFile : images) {
            saveImage(multipartFile.getOriginalFilename(), dir64, multipartFile.getBytes());
        }
        return "redirect:/cloud/" + dir64;
    }

    private static void saveImage(String fileName, String dir, byte[] bytes) throws Exception {
        OutputStream opStream = null;
        try {
            File f = new File("cloud");
            boolean b = !f.exists() && f.mkdir() || f.exists();
            if (!b) {
                throw new IOException("no create cloud");
            }
            f = new File("cloud/" + dir);
            b = !f.exists() && f.mkdir() || f.exists();
            if (!b) {
                throw new IOException("no create " + dir);
            }
            f = new File("cloud/" + dir + "/" + fileName);
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
    }

    private static byte[] readFileToByteArray(File file) {
        FileInputStream fis;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
        return bArray;
    }

    public static void write(final HttpServletResponse response, File file) throws IOException {
        final ServletOutputStream outputStream = response.getOutputStream();

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/download");
            response.setContentLength((int) file.length());
            String safariEncodedFileName = file.getName();
//            String defaultEncodedFileName = EncodeSupport.encodeAsURLEncoded(fileName);

//            String agent = request.getHeader("User-Agent");
//            boolean isSafari = agent != null && agent.indexOf("Safari") != -1;

//            if (isSafari) {
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + safariEncodedFileName + "\"");
//            }
//            else {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + safariEncodedFileName + "\"; filename*=UTF-8''" + safariEncodedFileName);
//            }
            InputStream in = new FileInputStream(file);
            byte[] readBuffer = new byte[128];
            int length;
            while ((length = in.read(readBuffer)) != -1) {
                outputStream.write(readBuffer, 0, length);
            }
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    private static void save(String fileName, String ext, byte[] bytes) {

        File file = new File(fileName + "." + ext);
//        BufferedImage image = ImageIO.read(new I);
        try {
            ImageIO.write(null, ext, file);  // ignore returned boolean
        } catch (IOException e) {
            System.out.println("Write error for " + file.getPath() +
                    ": " + e.getMessage());
        }
    }
}