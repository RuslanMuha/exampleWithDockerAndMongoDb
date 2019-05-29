package com.telran.dockermongo.controller;
import com.telran.dockermongo.model.Product;
import com.telran.dockermongo.repository.ProductRepository;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Value("${path.input}")
    private String basePath;

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

    @GetMapping("/store")
    public void toFile(@RequestParam(value = "perFile", required = false) String perFile) {
        File directory = new File(basePath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        List<Product> products = productRepository.findAll();


        String filename = "/products.json";
        if (perFile != null) {
            products.forEach((x) -> {
                        StringBuilder preFilename = new StringBuilder("/pruduct");
                        preFilename.append(x.getId()).append(".json");
                        PrintWriter out = write(basePath + preFilename, false);
                        out.close();

                    }
            );

        } else {
            PrintWriter out = write(basePath + filename, true);
            products.forEach((x) -> out.println(x));
            out.close();
        }


    }

    @SneakyThrows
    private PrintWriter write(String path, boolean isNorewrite) {
        PrintWriter out = new PrintWriter((new BufferedWriter(new FileWriter(path, isNorewrite))));
        return out;
    }


}