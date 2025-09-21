package com.distributed.product.application.usercase;

import com.distributed.product.application.ports.in.IGetStockUseCase;
import com.distributed.product.infrastructure.adapter.in.mapper.StockMapper;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import com.distributed.product.infrastructure.adapter.out.repository.IStockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetStockUseCase implements IGetStockUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetStockUseCase.class);

    private final IStockRepository repository;

    @Autowired
    public GetStockUseCase(IStockRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StockResource> fetchProductIdAndRetailId(String productId, String retailId) {
        log.info("[STOCK-SERVICE] Fetching stock for productId: {} and retailId: {}", productId, retailId);
        return repository.findByProductIdAndRetailId(productId, retailId).stream()
                .map(StockMapper::convertModelToResource)
                .toList();
    }
}
