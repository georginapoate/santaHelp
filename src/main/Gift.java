package main;

import java.util.Comparator;

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
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

class priceComparator implements Comparator<Gift> {
    @Override
    // cel mai scump:
    public int compare(Gift o1, Gift o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}