package controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/cloud/download/{dir}")
    public String download(Model model, @PathVariable("dir") String dir, final HttpServletResponse response) throws IOException {
        write(response, "cloud/" + dir);
        return "cloudMain";
    }

    @RequestMapping(value = "/cloud{dir}")
    public String watch(Model model, @PathVariable("dir") String dir) throws IOException {
        List<String> images = new LinkedList<>();
        List<String> names = new LinkedList<>();
        File directory = new File("cloud/" + dir);
        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                images.add("data:image/jpeg;base64," + new String(Base64.getEncoder().encode(readFileToByteArray(file)), StandardCharsets.UTF_8));
            }
        }
        model.addAttribute("images", images);
        return "cloudWatch";
    }

    @RequestMapping("/cloudAdd")
    @PostMapping
    public String cloudAdd(Model model, @RequestParam(name = "pro-image") List<MultipartFile> images,
                           @RequestParam(name = "dir") String dir) throws Exception {
        for (MultipartFile multipartFile : images) {
            saveImage(multipartFile.getOriginalFilename(), dir, multipartFile.getBytes());
        }
//        model.addAttribute("href", "http://yourSol.store/cloud/watch/" + dir);
        return "redirect:/cloud" + dir;
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

    public static void write(final HttpServletResponse response, String filePath) throws IOException {
        File dir = new File(filePath);
        if (!dir.exists()) {
            return;
        }
        final ServletOutputStream outputStream = response.getOutputStream();

        try {
            for (File file : dir.listFiles()) {
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
            }
        } finally {
        }
//        outputStream.flush();
//        outputStream.close();
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