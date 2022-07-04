package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("{pid}")
    public Product product(@PathVariable("pid") Integer pid){
        log.info("Next,Search No.{} product information", pid);
        Product product = productService.findByPid(pid);
        log.info("Search successfully,Content is {}", JSON.toJSONString(product));
        return product;
    }

}
