package dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private String id;
    private String status;
    private String done;
    private String track;
    private String info;
    private List<String> purchasesIds;
    private String price;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getPurchasesIds() {
        return purchasesIds;
    }

    public void setPurchasesIds(List<String> purchasesIds) {
        this.purchasesIds = purchasesIds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
