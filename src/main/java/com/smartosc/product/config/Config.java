package com.smartosc.product.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * product
 *
 * @author Tung lam
 * @created_at 04/06/2020 - 17:21
 * @created_by Tung lam
 * @since 04/06/2020
 */
@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
