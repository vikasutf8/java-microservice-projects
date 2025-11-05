package com.SpringBoot.RestfullApi;


import com.SpringBoot.RestfullApi.Entity.Address;
import com.SpringBoot.RestfullApi.Entity.Enum.OrderStatus;
import com.SpringBoot.RestfullApi.Entity.Order;
import com.SpringBoot.RestfullApi.Entity.Product;
import com.SpringBoot.RestfullApi.Repository.OrderRepository;
import com.SpringBoot.RestfullApi.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void TestCreateOrder(){
//embedding -- de-normalization
        //referencing --normalization
        List<String> tags = Arrays.asList("apple", "electronics", "premium");

        Product Iphone = Product.builder()
                .name("iPhone 16 Pro")
                .category("Smartphones")
                .price(1299.99)
                .tags(tags)
                .stock(25)
                .build();

        Product earpad = Product.builder()
                .name("Airpod 3 pro")
                .category("Airpiece")
                .price(1299.99)
                .tags(tags)
                .stock(25)
                .build();

        List<Product> savedProducts = productRepository.saveAll(List.of(Iphone, earpad));


        Address address = Address.builder()
                .street("123 MG Road")
                .city("Bangalore")
                .state("Karnataka")
                .postalCode("560001")
                .country("India")
                .build();
        Order order = Order.builder()
                .qty(5)
                .netPrice(999.99)
                .status(OrderStatus.PENDING)
                .address(address)
//                .products(List.of(Iphone,earpad))
                .products(savedProducts)
                .build();

        System.out.println(orderRepository.save(order));
    }

    @Test
    void testGetOrders() {
        List<Order> result = orderRepository.findByStatusAndQtyGreaterThan(OrderStatus.PENDING, 6);
//        System.out.println("✅ Found Orders: " + result.size());
//        result.forEach(System.out::println);

//        String orderIds = result.stream()
//                .map(Order::getId)
//                .collect(Collectors.joining(", "));
//
//        System.out.println("✅ Found Orders with IDs: " + orderIds);
//
//        List<Order> result1 = orderRepository.findByStatusAndQtyGreaterThanOrderByCreatedAtDesc(OrderStatus.PENDING, 6);
//        String orderIds1 = result1.stream()
//                .map(Order::getId)
//                .collect(Collectors.joining(", "));
//
//        System.out.println("✅ Found Orders with IDs: " + orderIds1);
//        List<Order> result12 = orderRepository.findPendingOrdersAbovePrice(OrderStatus.PENDING, 500.00);
//
//        Pageable pageable = PageRequest.of(2,5, Sort.by(Sort.Direction.DESC, "netPrice"));
//        List<Order> listALlOrder =orderRepository.findAll(pageable).toList();

        Pageable pageable = PageRequest.of(2, 5, Sort.by(Sort.Direction.DESC, "id"));

        List<Order> listAllOrders = orderRepository.findByAddressCity("Delhi", pageable);

        listAllOrders.forEach(System.out::println);
    }

    @Test
    void testDeleteOrders(){
        List<Order> result1 = orderRepository.findByStatusAndQtyGreaterThanOrderByCreatedAtDesc(OrderStatus.CANCELLED, 2);

        orderRepository.deleteAll(result1);

        List<Order> result2 = orderRepository.findByStatusAndQtyGreaterThanOrderByCreatedAtDesc(OrderStatus.CANCELLED, 2);

        String orderIds1 = result1.stream()
                .map(Order::getId)
                .collect(Collectors.joining(", "));

        System.out.println("✅ Found Orders with IDs: " + orderIds1);
    }
}
