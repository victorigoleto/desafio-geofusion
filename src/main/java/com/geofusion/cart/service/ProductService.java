package com.geofusion.cart.service;



import com.geofusion.cart.model.Product;
import com.geofusion.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> readAll() {
        return repository.findAll();
    }

    public Product read(Long id) {
        return repository.findById(id).orElse(null);

    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product newProduct) {
        return repository.findById(id).map(product -> {
            product.setDescription(newProduct.getDescription());
            return repository.save(product);
        }).orElseGet(() -> {
            return null;
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
