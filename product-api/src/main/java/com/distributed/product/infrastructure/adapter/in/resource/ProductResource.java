package com.distributed.product.infrastructure.adapter.in.resource;

import java.math.BigDecimal;
import java.util.List;

public class ProductResource {
    private String productId;
    private String retailId;
    private String name;
    private String description;
    private BigDecimal price;
    private String createdAt;
    private String updatedAt;
    private List<StockResource> stock;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRetailId() {
        return retailId;
    }

    public void setRetailId(String retailId) {
        this.retailId = retailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<StockResource> getStock() {
        return stock;
    }

    public void setStock(List<StockResource> stock) {
        this.stock = stock;
    }
}
