package main;

public class Gift {
    private String productName;
    private Double price;
    private String category;
    private Integer quantity;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer obtainQuantity() {
        return quantity;
    }  // don't want to show it at output, changed name from getter

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}