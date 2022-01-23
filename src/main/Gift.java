package main;

public class Gift {
    private String productName;
    private Double price;
    private String category;
    private Integer quantity;

    /**
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * @return
     */
    public Integer obtainQuantity() {
        return quantity;
    }  // don't want to show it at output, changed name from getter

    /**
     * @param quantity
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
