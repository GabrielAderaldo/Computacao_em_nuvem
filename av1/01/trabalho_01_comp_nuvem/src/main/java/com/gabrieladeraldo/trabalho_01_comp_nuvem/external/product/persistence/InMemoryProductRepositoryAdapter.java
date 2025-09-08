package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.product.persistence;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.Product;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProductRepositoryAdapter implements ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public <S extends Product> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(sequence.incrementAndGet());
        }
        products.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }
}
