package com.smartosc.product.repository;

import com.smartosc.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * product
 *
 * @author Tung lam
 * @created_at 04/06/2020 - 17:38
 * @created_by Tung lam
 * @since 04/06/2020
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
