package com.smartosc.product;

import com.smartosc.product.aop.services.ApiLogService;
import com.smartosc.product.aop.services.DomainService;
import com.smartosc.product.entity.ApiLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * product
 *
 * @author Tung lam
 * @created_at 12/06/2020 - 15:50
 * @created_by Tung lam
 * @since 12/06/2020
 */
public class AopSpringBootTest {
    @Autowired
    private DomainService service;
    @Autowired
    private ApiLogService apiLogService;

    @Test
    public void testGetDomainObjectById() {
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> service.getDomainObjectById(0L));
        List<ApiLog> apiLogs = apiLogService.list();
        assertEquals(apiLogs.size(), 1);
    }
}
