package com.distributed.product.infrastructure.adapter.in.resource;


import jakarta.validation.constraints.NotNull;

public class StockMovementResource {
    @NotNull private String productId;
    @NotNull private String retailId;
    @NotNull private Integer numberOfElement;
    @NotNull private String warehouseId;

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

    public Integer getNumberOfElement() {
        return numberOfElement;
    }

    public void setNumberOfElement(Integer numberOfElement) {
        this.numberOfElement = numberOfElement;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }
}
