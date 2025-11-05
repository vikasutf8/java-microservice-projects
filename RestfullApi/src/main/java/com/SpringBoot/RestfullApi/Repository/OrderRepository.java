package com.SpringBoot.RestfullApi.Repository;

import com.SpringBoot.RestfullApi.Entity.Enum.OrderStatus;
import com.SpringBoot.RestfullApi.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String > {

//    List<Order> findByStatus(OrderStatus status);

    List<Order> findByStatusAndQtyGreaterThan(OrderStatus status, Integer qty);


    List<Order> findByStatusAndQtyGreaterThanOrderByCreatedAtDesc(OrderStatus status, Integer qty);


    @Query("{ 'status': ?0, 'netPrice': { $gte: ?1 } }")
    List<Order> findPendingOrdersAbovePrice(OrderStatus status, Double price);
}
