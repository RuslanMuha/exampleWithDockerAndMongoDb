package com.telran.dockermongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "products_collection")
public class Product {


    @Id
    private String id;

    private String productName;

    private Double productPrice;

    private String seller;
}
