package com.distributed.product.infrastructure.adapter.in.mapper;

import com.distributed.product.infrastructure.adapter.in.resource.ProductResource;
import com.distributed.product.infrastructure.adapter.out.model.Product;

public class ProductMapper {
    public static ProductResource convertModelToResource(Product product) {
        ProductResource productResource = new ProductResource();
        productResource.setProductId(product.getProductId());
        productResource.setRetailId(product.getRetailId());
        productResource.setName(product.getName());
        productResource.setDescription(product.getDescription());
        productResource.setPrice(product.getPrice());
        productResource.setCreatedAt(product.getCreatedAt().toString());
        productResource.setUpdatedAt(product.getUpdatedAt().toString());
        return productResource;
    }
}
