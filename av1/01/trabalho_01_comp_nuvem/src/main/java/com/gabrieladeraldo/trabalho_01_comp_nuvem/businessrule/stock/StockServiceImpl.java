package com.gabrieladeraldo.trabalho_01_comp_nuvem.businessrule.stock;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.Stock;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.StockRepository;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> getAllStockItems() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> getStockByProductId(Long productId) {
        return stockRepository.findByProdutoId(productId);
    }

    @Override
    public Stock updateStockQuantity(Long id, Integer quantidade) {
        return stockRepository.findById(id)
                .map(stock -> {
                    stock.setQuantidade(quantidade);
                    return stockRepository.save(stock);
                }).orElseThrow(() -> new RuntimeException("Stock item not found with id " + id));
    }
}
