package com.distributed.product.infrastructure.adapter.out.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "stock")
public class Stock extends Auditable {

    @Id
    @Column(name = "stock_id")
    private String id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "retail_id")
    private String retailId;

    @Column(name = "warehouse_id")
    private String warehouseId;

    @Column(name = "available_quantity")
    private int availableQuantity;

    @Column(name = "committed_quantity")
    private int committedQuantity;

    @Column(name = "on_hand_quantity")
    private int onHandQuantity;

    @Version
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getCommittedQuantity() {
        return committedQuantity;
    }

    public void setCommittedQuantity(int committedQuantity) {
        this.committedQuantity = committedQuantity;
    }

    public int getOnHandQuantity() {
        return onHandQuantity;
    }

    public void setOnHandQuantity(int onHandQuantity) {
        this.onHandQuantity = onHandQuantity;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", retailId='" + retailId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", availableQuantity=" + availableQuantity +
                ", committedQuantity=" + committedQuantity +
                ", onHandQuantity=" + onHandQuantity +
                ", version=" + version +
                '}';
    }
}
