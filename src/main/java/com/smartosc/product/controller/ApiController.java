package com.smartosc.product.controller;

import com.smartosc.product.dto.UserDTO;
import com.smartosc.product.entity.APIResponse;
import com.smartosc.product.repository.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * product
 *
 * @author Tung lam
 * @created_at 12/06/2020 - 11:03
 * @created_by Tung lam
 * @since 12/06/2020
 */
@RestController
@RequestMapping("/api/users")
public class ApiController {
    @Autowired
    private RestTemplateService restTemplateService;

    @Value("${service.hostname}")
    private String hostname;

    @Value("${service.userApi}")
    private String userApi;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody UserDTO userDTO) {
        String url = hostname.concat(userApi).concat("/authenticate");
        return ResponseEntity.ok(restTemplateService.getToken(url, HttpMethod.POST, null, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable("id") Long id) {
        String url = hostname.concat(userApi).concat("/delete") + id;

        ParameterizedTypeReference<APIResponse<UserDTO>> responseParameterizedTypeReference = new ParameterizedTypeReference<APIResponse<UserDTO>>() {
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("");

        return ResponseEntity.ok(restTemplateService.getSomething(url, HttpMethod.DELETE, headers, null, responseParameterizedTypeReference));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        String url = hostname.concat(userApi).concat("/register");
        ParameterizedTypeReference<APIResponse<UserDTO>> responseParameterizedTypeReference = new ParameterizedTypeReference<APIResponse<UserDTO>>() {
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("");
        return ResponseEntity.ok(restTemplateService.getSomething(url, HttpMethod.POST, headers, userDTO, responseParameterizedTypeReference));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        String url = hostname.concat(userApi).concat("/update") + id;
        ParameterizedTypeReference<APIResponse<UserDTO>> responseParameterizedTypeReference = new ParameterizedTypeReference<APIResponse<UserDTO>>() {
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("");
        return ResponseEntity.ok(restTemplateService.getSomething(url, HttpMethod.PUT, headers, userDTO, responseParameterizedTypeReference));
    }
}
