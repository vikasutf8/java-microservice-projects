package com.SpringBoot.RestfullApi.Entity;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("category")
    @Indexed
    private String category;

    @Field("price")
    @Indexed
    private Double price;

    @Field("tags")
    private List<String> tags;   // e.g. ["electronics", "sale", "new"]

    @Field("stock")
    private Integer stock;


}
