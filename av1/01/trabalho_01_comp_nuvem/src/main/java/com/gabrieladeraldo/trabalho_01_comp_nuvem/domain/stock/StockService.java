package com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {
    Stock addStock(Stock stock);
    List<Stock> getAllStockItems();
    Optional<Stock> getStockByProductId(Long productId);
    Stock updateStockQuantity(Long id, Integer quantidade);
}
