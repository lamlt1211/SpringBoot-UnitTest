package com.smartosc.product.repository;

import com.smartosc.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * product
 *
 * @author Tung lam
 * @created_at 22/06/2020 - 14:39
 * @created_by Tung lam
 * @since 22/06/2020
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
