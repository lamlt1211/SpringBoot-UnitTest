package com.smartosc.product.repository;

import com.smartosc.product.entity.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * product
 *
 * @author Tung lam
 * @created_at 12/06/2020 - 15:43
 * @created_by Tung lam
 * @since 12/06/2020
 */
public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
}
