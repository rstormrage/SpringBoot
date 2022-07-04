package com.itheima.controller;


import com.alibaba.fastjson.JSON;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/orders/products")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping ("{pid}")
    public Orders createOrders(@PathVariable("pid") Integer pid){
        log.info("Order of No.{} product received, then calling product information by using Microservice", pid);


        //ribbon loading balance
        Product product =
                this.restTemplate.getForObject("http://service-product/products/" + pid, Product.class);
        log.info("Got No.{} information is", pid, JSON.toJSONString(product));


        Orders orders = new Orders();
        orders.setUid(1);
        orders.setUsername("TestUser");
        orders.setPid(pid);
        orders.setPname(product.getPname());
        orders.setPprice(product.getPprice());
        orders.setNumber(1);

        orderService.createOrder(orders);
        log.info("Created Order successfully, order info{}", JSON.toJSONString(orders));

        return orders;
    }
}


//
//
//@RestController
//@RequestMapping("/orders/products")
//@Slf4j
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @RequestMapping("{pid}")
//    public Orders orders(@PathVariable("pid") Integer pid){
//        log.info("Order of No.{} product received, then calling product information by using Microservice", pid);
//        Product product = restTemplate.getForObject("http://localhost:8081/products/"+pid, Product.class);
//        log.info("Got No.{} information is", pid, JSON.toJSONString(product));
//
//
//        Orders orders = new Orders();
//        orders.setUid(1);
//        orders.setUsername("TestUser");
//        orders.setPid(pid);
//        orders.setPname(product.getPname());
//        orders.setPprice(product.getPprice());
//        orders.setNumber(1);
//
//        orderService.createOrder(orders);
//        log.info("Created Order successfully, order info{}", JSON.toJSONString(orders));
//
//        return orders;
//    }
//}