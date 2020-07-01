package dto;

public class ItemDto implements Comparable<ItemDto> {
    private String id;
    private String title;
    private String price;
    private String description;
    private String additionalItem;
    private String reservation = "false";
    private String hide = "false";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalItem() {
        return additionalItem;
    }

    public void setAdditionalItem(String additionalItem) {
        this.additionalItem = additionalItem;
    }

    public String getReservation() {
        return "false";
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    @Override
    public int compareTo(ItemDto o) {
        return this.getId().compareTo(o.getId());
    }
}
