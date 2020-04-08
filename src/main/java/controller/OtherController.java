package controller;

import dto.OrderDto;
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

import static helpers.FileHelper.write;
import static helpers.ItemHelper.removeOrReservationFromCsv;

@Controller
public class OtherController {

    @RequestMapping("/download")
    public void download(@RequestParam("name") String name,
                         final HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(name)) return;
        final File directory = new File("img");
        if (!directory.exists()) return;
        final File[] fileList = directory.listFiles();
        if (fileList == null) return;
        for (File file : fileList) {
            if (file.getName().contains(name)) {
                write(response, file);
            }
        }
    }

    @RequestMapping("/submitOrder")
    @PostMapping
    public RedirectView submitOrder(RedirectAttributes attributes, OrderDto orderDto) throws IOException {
        removeOrReservationFromCsv(orderDto.getPurchasesIds(), true);

        attributes.addAttribute("n", "");
        return new RedirectView("/confirm");
    }

    @RequestMapping("/order")
    @PostMapping
    public String order(Model model, @RequestParam(name = "n") String n) throws IOException {
        //get from db purchase
        return "order";
    }
}