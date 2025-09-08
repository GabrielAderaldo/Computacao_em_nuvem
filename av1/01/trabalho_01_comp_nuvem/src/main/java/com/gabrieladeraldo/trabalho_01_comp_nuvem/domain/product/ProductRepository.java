package com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {
    <S extends Product> S save(S entity);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
