package controller;

import dto.ItemDto;
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
import java.util.List;

import static db.CacheDb.orderMap;
import static db.CacheDb.orderNumberMap;
import static service.FileService.write;
import static service.ItemService.findById;
import static service.ItemService.removeOrReservation;
import static service.OrderService.addAndGetId;

@Controller
public class OtherController {
    @RequestMapping("/download")
    public void download(@RequestParam String id,
                         final HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(id)) return;
        final File directory = new File("img");
        if (!directory.exists()) return;
        final File[] fileList = directory.listFiles();
        if (fileList == null) return;
        for (File file : fileList) {
            if (file.getName().contains(id)) {
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
    ) throws IOException {
        if ((id == null || id.isEmpty()) && (number == null || number.isEmpty())) return "orderNotFound";
        final OrderDto orderDto;
        if (!StringUtils.isEmpty(id)) {
            orderDto = orderMap.get(id);
            if (orderDto == null || orderDto.getId() == null) {
                return "orderNotFound";
            }
        } else if (!StringUtils.isEmpty(number)) {
            orderDto = orderNumberMap.get(number);
            if (orderDto == null || orderDto.getId() == null) {
                return "orderNotFound";
            }
        } else {
            return "orderNotFound";
        }
        List<ItemDto> purchasesDtoList = orderDto.getPurchasesDtoList();
        if (purchasesDtoList.remove(null)) {
            purchasesDtoList.clear();
            purchasesDtoList.addAll(findById(orderDto.getPurchasesIds()));
        }

        model.addAttribute("order", orderDto);

        return "order";
    }
}