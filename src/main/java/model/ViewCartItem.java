package model;

public class ViewCartItem {

    private String productName;
    private double price;
    private int quantity;

    public ViewCartItem(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product = " + productName +
                ", Price = " + price +
                ", Quantity = " + quantity;
    }
}