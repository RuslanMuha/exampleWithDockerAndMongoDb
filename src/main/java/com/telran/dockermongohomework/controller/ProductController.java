package com.telran.dockermongohomework.controller;

import com.telran.dockermongohomework.model.Product;
import com.telran.dockermongohomework.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    //CRUD
    //C - Create
    //R - Read
    //U - Update
    //D - Delete


    @PostMapping("/products")
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public Product update(@PathVariable("id") String id,
                          @RequestParam(value = "seller", required = false) String seller,
                          @RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "price", required = false) Double price) {

        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }

        if (seller != null) {
            product.setSeller(seller);
        }

        if (price != null) {
            product.setProductPrice(price);
        }

        if (name != null) {
            product.setProductName(name);
        }

        return productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") String id) {
        productRepository.deleteById(id);
    }

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable("id") String id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("/products")
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
