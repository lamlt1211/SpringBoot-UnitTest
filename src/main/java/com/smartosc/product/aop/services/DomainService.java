package com.smartosc.product.aop.services;


import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * product
 *
 * @author Tung lam
 * @created_at 12/06/2020 - 15:41
 * @created_by Tung lam
 * @since 12/06/2020
 */
@Service
public class DomainService {
    public Object getDomainObjectById(Long id) throws Exception {
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            //do some logging
        }
        if (id == 0) throw new NullPointerException("Null ty");
        return id;
    }
}
