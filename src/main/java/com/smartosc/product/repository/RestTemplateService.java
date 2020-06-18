package com.smartosc.product.repository;

import com.smartosc.product.entity.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * product
 *
 * @author Tung lam
 * @created_at 11/06/2020 - 17:29
 * @created_by Tung lam
 * @since 11/06/2020
 */
@Service
public class RestTemplateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplate.class);

    @Autowired
    private RestTemplate restTemplate;

    public <T> T getSomething(String url, HttpMethod method, HttpHeaders headers, Object body, ParameterizedTypeReference<APIResponse<T>> responseType) {
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        ResponseEntity<APIResponse<T>> res = restTemplate.exchange(url, method, entity, responseType);
        if (res.getStatusCodeValue() >= HttpStatus.OK.value() && res.getStatusCodeValue() < HttpStatus.MULTIPLE_CHOICES.value()) {
            return res.getBody().getData();
        }
        LOGGER.error(res.getBody().getMessage());
        throw new IllegalArgumentException(res.getBody().getMessage());
    }

    public String getToken(String url, HttpMethod method, HttpHeaders headers, Object body) {
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> res = restTemplate.exchange(url, method, entity, String.class);
        if (res.getStatusCodeValue() >= HttpStatus.OK.value() && res.getStatusCodeValue() < HttpStatus.MULTIPLE_CHOICES.value()) {
            return res.getBody();
        }
        throw new IllegalArgumentException(res.getBody());
    }
}
