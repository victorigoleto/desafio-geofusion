package com.geofusion.cart.controller;

import com.geofusion.cart.model.Product;
import com.geofusion.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity read() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable("id") Long id) {
        Product product = productService.read(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse produto não existe!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Product product, @PathVariable Long id) {
        Product updatedProduct = productService.update(id, product);
        if (updatedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse produto não existe!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Product product = productService.read(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse produto não existe!");
        }
        productService.delete(product.getCode());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
    }

}