package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.product.web;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.external.product.web.ProductRequestDTO;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.external.product.web.ProductResponseDTO;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.Product;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product product = convertToEntity(productRequestDTO);
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(convertToDto(savedProduct), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        try {
            Product productDetails = convertToEntity(productRequestDTO);
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(convertToDto(updatedProduct));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    private Product convertToEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setNome(productRequestDTO.getNome());
        product.setDescricao(productRequestDTO.getDescricao());
        product.setPreco(productRequestDTO.getPreco());
        return product;
    }

    private ProductResponseDTO convertToDto(Product product) {
        return new ProductResponseDTO(product.getId(), product.getNome(), product.getDescricao(), product.getPreco());
    }
}
