package com.telran.dockermongohomework.repository;

import com.telran.dockermongohomework.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
