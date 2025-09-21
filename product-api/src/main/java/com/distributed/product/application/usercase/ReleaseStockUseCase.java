package com.distributed.product.application.usercase;

import com.distributed.product.application.ports.in.IReleaseStockUseCase;
import com.distributed.product.domain.exception.CommitedStockException;
import com.distributed.product.domain.exception.NotFoundException;
import com.distributed.product.domain.exception.StockVersionException;
import com.distributed.product.infrastructure.adapter.in.mapper.StockMapper;
import com.distributed.product.infrastructure.adapter.in.resource.StockMovementResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import com.distributed.product.infrastructure.adapter.out.model.Stock;
import com.distributed.product.infrastructure.adapter.out.repository.IStockRepository;
import jakarta.persistence.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReleaseStockUseCase implements IReleaseStockUseCase {
    private static final Logger log = LoggerFactory.getLogger(ReleaseStockUseCase.class);

    private final IStockRepository repository;

    @Autowired
    public ReleaseStockUseCase(IStockRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public StockResource release(StockMovementResource release) {
        log.info("[RELEASE-STOCK-USE-CASE]Releasing stock for: {}", release);
        final Stock stock = repository.findByProductIdAndRetailIdAndWarehouseId(release.getProductId(), release.getRetailId(), release.getWarehouseId()).map(it -> {
            this.verifyCommittedStock(it);

            it.setCommittedQuantity(it.getCommittedQuantity() - release.getNumberOfElement());
            it.setAvailableQuantity(it.getAvailableQuantity() + release.getNumberOfElement());

            return this.save(it);
        }).orElseThrow(() -> {
            log.warn("[RELEASE-STOCK-USE-CASE] Stock not found for: {}", release);
            return new NotFoundException("Stock not found");
        });


        return StockMapper.convertModelToResource(stock);
    }

    private void verifyCommittedStock(Stock stock) {
        if (stock.getCommittedQuantity() <= 0) {
            log.warn("[RELEASE-STOCK-USE-CASE] No committed stockId: {}", stock.getId());
            throw new CommitedStockException("No committed stock");
        }
    }

    private Stock save(Stock stock) {
        try {
            log.info("[RELEASE-STOCK-USE-CASE] Saving stock: {}", stock);
            return repository.save(stock);
        } catch (OptimisticLockException ex) {
            log.info("[RELEASE-STOCK-USE-CASE] OptimisticLockException occurred while saving stock: {}", stock);
            throw new StockVersionException("Stock version conflict, please retry");
        }
    }
}
