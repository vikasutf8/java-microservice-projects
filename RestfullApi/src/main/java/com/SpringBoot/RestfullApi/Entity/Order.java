package com.SpringBoot.RestfullApi.Entity;


import com.SpringBoot.RestfullApi.Entity.Enum.OrderStatus;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "orders")
@CompoundIndex(name = "idx_qty_status" ,def = "{'qty':-1, 'status':1}") // -1 is oreder of asc or 1 is descending
@CompoundIndex(name = "idx_address_city",def = "{'address.city': 1}")
public class Order {

    @Id
    private String id;

    private  Integer qty;

    private Double netPrice;

    @Indexed
    private OrderStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Address address;

// product already stored in db ... not cascading
    @DBRef(lazy = true)
    private List<Product> products;
}
