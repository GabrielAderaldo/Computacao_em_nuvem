package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.stock.web;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.external.stock.web.StockRequestDTO;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.external.stock.web.StockResponseDTO;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.Stock;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> addStock(@RequestBody StockRequestDTO stockRequestDTO) {
        Stock stock = convertToEntity(stockRequestDTO);
        Stock savedStock = stockService.addStock(stock);
        return new ResponseEntity<>(convertToDto(savedStock), HttpStatus.CREATED);
    }

    @GetMapping
    public List<StockResponseDTO> getAllStockItems() {
        return stockService.getAllStockItems().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<StockResponseDTO> getStockByProductId(@PathVariable Long productId) {
        return stockService.getStockByProductId(productId)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> updateStockQuantity(@PathVariable Long id, @RequestBody Integer quantidade) {
        try {
            Stock updatedStock = stockService.updateStockQuantity(id, quantidade);
            return ResponseEntity.ok(convertToDto(updatedStock));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private Stock convertToEntity(StockRequestDTO stockRequestDTO) {
        Stock stock = new Stock();
        stock.setProdutoId(stockRequestDTO.getProdutoId());
        stock.setQuantidade(stockRequestDTO.getQuantidade());
        return stock;
    }

    private StockResponseDTO convertToDto(Stock stock) {
        return new StockResponseDTO(stock.getId(), stock.getProdutoId(), stock.getQuantidade());
    }
}
