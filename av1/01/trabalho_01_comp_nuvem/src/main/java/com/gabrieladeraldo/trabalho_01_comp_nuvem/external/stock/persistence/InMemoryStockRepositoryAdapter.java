package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.stock.persistence;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.Stock;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.StockRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryStockRepositoryAdapter implements StockRepository {

    private final Map<Long, Stock> stockItems = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public <S extends Stock> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(sequence.incrementAndGet());
        }
        stockItems.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return Optional.ofNullable(stockItems.get(id));
    }

    @Override
    public List<Stock> findAll() {
        return new ArrayList<>(stockItems.values());
    }

    @Override
    public Optional<Stock> findByProdutoId(Long produtoId) {
        return stockItems.values().stream()
                .filter(stock -> stock.getProdutoId().equals(produtoId))
                .findFirst();
    }
}
