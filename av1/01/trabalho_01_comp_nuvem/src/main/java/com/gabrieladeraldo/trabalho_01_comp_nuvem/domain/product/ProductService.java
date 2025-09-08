package com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
}
