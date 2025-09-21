package com.distributed.product.application.service;

import com.distributed.product.application.ports.in.IGetProductUseCase;
import com.distributed.product.application.ports.in.IGetStockUseCase;
import com.distributed.product.domain.exception.NotFoundException;
import com.distributed.product.infrastructure.adapter.in.mapper.ProductMapper;
import com.distributed.product.infrastructure.adapter.in.resource.ProductResource;
import com.distributed.product.infrastructure.adapter.out.repository.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductUseCase implements IGetProductUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetProductUseCase.class);

    private final IProductRepository repository;
    private final IGetStockUseCase getStockUseCase;

    @Autowired
    public GetProductUseCase(IProductRepository repository,
                             IGetStockUseCase getStockUseCase) {
        this.repository = repository;
        this.getStockUseCase = getStockUseCase;
    }

    @Override
    public List<ProductResource> getProductByRetailId(String retailId) {
        log.error("[GET-PRODUCT-USE-CASE] Get product by retailId: {}", retailId);
        return repository.findByRetailId(retailId)
                .stream()
                .map(product -> {
                    final ProductResource resource = ProductMapper.convertModelToResource(product);
                    resource.setStock(getStockUseCase.fetchProductIdAndRetailId(resource.getProductId(), retailId));
                    return resource;
                }).toList();
    }

    @Override
    public ProductResource getProductByIdAndRetailId(String productId, String retailId) {
        log.info("[GET-PRODUCT-USE-CASE] Get product by productId: {} and retailId: {}", productId, retailId);
        return repository.findByProductIdAndRetailId(productId, retailId)
                .map(product -> {
                    final ProductResource resource = ProductMapper.convertModelToResource(product);
                    resource.setStock(getStockUseCase.fetchProductIdAndRetailId(resource.getProductId(), retailId));
                    return resource;
                }).orElseThrow(() -> {
                    log.error("[GET-PRODUCT-USE-CASE] Product not found for productId: {} and retailId: {}", productId, retailId);
                    return new NotFoundException("Product not found");
                });
    }
}
