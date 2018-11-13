package com.telran.dockermongohomework.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Product {
    //1. Product -> product
    //2. product -> products

    @Id
    private String id;

    private String productName;

    private Double productPrice;

    private String seller;
}
