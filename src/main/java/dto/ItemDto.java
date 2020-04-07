package dto;

public class ItemDto {
    private String id;
    private String title;
    private String price;
    private String description;
    private String instagramLikeUrl;
    private String reservation;

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

    public String getInstagramLikeUrl() {
        return instagramLikeUrl;
    }

    public void setInstagramLikeUrl(String instagramLikeUrl) {
        this.instagramLikeUrl = instagramLikeUrl;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }
}
