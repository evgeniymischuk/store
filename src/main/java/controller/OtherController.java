package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static helpers.FileHelper.write;
import static helpers.ItemsHelper.removeOrReservationFromCsv;

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
    public RedirectView submitConfirm(RedirectAttributes attributes, @RequestParam List<String> ids) throws IOException {
        removeOrReservationFromCsv(ids, true);
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("n", "1234");
        return new RedirectView("/confirm");
    }

    @RequestMapping("/confirm")
    @PostMapping
    public String confirm(Model model, @RequestParam(name = "n") String n) throws IOException {
        //get from db purchase
        return "confirm";
    }
}