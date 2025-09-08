package com.gabrieladeraldo.trabalho_01_comp_nuvem.businessrule.product;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.Product;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.ProductRepository;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setNome(productDetails.getNome());
                    product.setDescricao(productDetails.getDescricao());
                    product.setPreco(productDetails.getPreco());
                    return productRepository.save(product);
                }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
