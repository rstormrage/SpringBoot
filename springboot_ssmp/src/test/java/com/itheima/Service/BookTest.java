package com.itheima.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookTest {
    @Autowired
    private BookService bookService;

    @Test
    void testGetOne(){
        System.out.println(bookService.getById(1));
    }

    @Test
    void testGetAll(){
        System.out.println(bookService.list());
    }
}
