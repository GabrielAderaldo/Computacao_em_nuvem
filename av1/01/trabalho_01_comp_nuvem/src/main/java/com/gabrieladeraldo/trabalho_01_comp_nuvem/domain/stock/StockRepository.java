package com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository {
    <S extends Stock> S save(S entity);
    Optional<Stock> findById(Long id);
    List<Stock> findAll();
    Optional<Stock> findByProdutoId(Long produtoId);
}
