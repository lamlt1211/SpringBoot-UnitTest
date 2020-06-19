package com.smartosc.product.controller;

import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.entity.APIResponse;
import com.smartosc.product.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.Valid;
import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 04/06/2020 - 17:32
 * @created_by Tung lam
 * @since 04/06/2020
 */
@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final String SUCCESS = "Success";

    /**
     *
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/getAll")
    public ResponseEntity<APIResponse<List<ProductDTO>>> getAllProduct() throws NotFoundException {
        List<ProductDTO> productDTOS = productService.getAllProduct();
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.OK.value(), SUCCESS, productDTOS), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> getById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.OK.value(), SUCCESS, productService.getById(id)), HttpStatus.OK);
    }

    /**
     * @param productDTO
     * @return
     * @throws MethodArgumentNotValidException
     * @throws NoHandlerFoundException
     */

    @PostMapping("/create")
    public ResponseEntity<APIResponse<ProductDTO>> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.OK.value(), SUCCESS, productService.create(productDTO)), HttpStatus.OK);
    }

    /**
     * @param id
     * @param productDTO
     * @return
     * @throws NotFoundException
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO productDTO) throws NotFoundException {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.OK.value(), SUCCESS, productService.update(id, productDTO)), HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> delete(@PathVariable Long id) throws NotFoundException {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
