// 22110311 - Tô Hữu Đức
package com.example.cosmesticapp.model;

public class Product {
    private Long productId;
    private String productName;
    private String image;

    public Product(Long productId, String productName, String image) {
        this.productId = productId;
        this.productName = productName;
        this.image = image;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
