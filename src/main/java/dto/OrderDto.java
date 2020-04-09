package dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utils.CommonUtil.join;

public class OrderDto {
    private String id;
    private String name;
    private String email;
    private String postal;
    private String number;
    private String status;
    private String done = "false";
    private String track = "";
    private String info = "";
    private List<String> purchasesIds;
    private List<ItemDto> purchasesDtoList;
    private String priceTotal;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPurchasesIdsString() {
        return join(purchasesIds);
    }

    public List<String> getPurchasesIds() {
        return purchasesIds;
    }

    public void setPurchasesIds(List<String> purchasesIds) {
        this.purchasesIds = purchasesIds;
    }

    public List<ItemDto> getPurchasesDtoList() {
        if (purchasesDtoList == null) {
            purchasesDtoList = new ArrayList<>();
        }
        return purchasesDtoList;
    }

    public void setPurchasesDtoList(List<ItemDto> purchasesDtoList) {
        this.purchasesDtoList = purchasesDtoList;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getDate() {
        return date;
    }

    public String getDateString() {
        final Date d = new Date();
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            d.setTime(Long.parseLong(this.date));
        } catch (NumberFormatException ignored) {
        }
        return dateFormat.format(d);
    }

    public void setDate(String date) {
        this.date = date;
    }
}
