package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static db.Helper.removeFromCsv;
import static db.Helper.write;

@Controller
public class OtherController {

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

    @RequestMapping("/submitConfirm")
    @PostMapping
    public String submitConfirm(Model model,  @RequestParam List<String> ids) throws IOException {
        removeFromCsv(ids);
        return "redirect:/confirm?n=1";
    }

    @RequestMapping("/confirm")
    @PostMapping
    public String confirm(Model model,  @RequestParam(name = "n") String n) throws IOException {
        model.addAttribute("purchaseNumber", "");
        model.addAttribute("purchaseNumber", "");
        return "confirm";
    }
}