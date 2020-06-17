package com.smartosc.product.aop.services;

import com.smartosc.product.entity.ApiLog;
import com.smartosc.product.repository.ApiLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 12/06/2020 - 15:40
 * @created_by Tung lam
 * @since 12/06/2020
 */
@Service
public class ApiLogService {
    @Autowired
    private ApiLogRepository apiLogRepository;

    public List<ApiLog> list() {
        return apiLogRepository.findAll();
    }

    public void saveApiLog(ApiLog apiLog) {
        apiLogRepository.save(apiLog);
    }
}
