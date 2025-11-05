package com.SpringBoot.RestfullApi;


import com.SpringBoot.RestfullApi.Entity.Address;
import com.SpringBoot.RestfullApi.Entity.Enum.OrderStatus;
import com.SpringBoot.RestfullApi.Entity.Order;
import com.SpringBoot.RestfullApi.Repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void TestCreateOrder(){
//embedding -- de-normalization
        //referencing --normalization
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
