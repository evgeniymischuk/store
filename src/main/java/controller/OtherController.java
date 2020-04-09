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
import java.util.Map;

import static db.CacheDb.orderMap;
import static service.FileService.write;
import static service.ItemService.removeOrReservation;
import static service.OrderService.addAndGetId;

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
        removeOrReservation(orderDto.getPurchasesIds(), true);
        final String id = addAndGetId(orderDto);
        attributes.addFlashAttribute("id", id);
        attributes.addAttribute("id", id);

        return new RedirectView("/order");
    }

    @RequestMapping("/order")
    public String order(Model model,
                        @RequestParam(name = "id", required = false) String id,
                        @RequestParam(name = "number", required = false) String number
    ) {
        if ((id == null || id.isEmpty()) && (number == null || number.isEmpty())) return "orderNotFound";
        final Map<String, OrderDto> map = orderMap;
        for (Map.Entry<String, OrderDto> orderDto: map.entrySet()){
            orderDto.getKey();
        }
        final OrderDto orderDto = orderMap.get(id);
        if (orderDto == null || orderDto.getId() == null) return "orderNotFound";

        model.addAttribute("orderDto", orderDto);

        return "order";
    }
}