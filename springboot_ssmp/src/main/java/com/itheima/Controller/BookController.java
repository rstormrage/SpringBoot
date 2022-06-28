package com.itheima.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.Controller.utils.R;
import com.itheima.Entity.Book;
import com.itheima.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public R getAll(){

        return new R(true,bookService.list());
    }

    @PostMapping
    public R save(@RequestBody Book book) throws IOException {
        //if(book.getName().equals("123")) throw new IOException();
        boolean flag = bookService.save(book);
        return new R(flag, flag? "添加成功^_^" : "添加失败-_-!" );

    }

    @PutMapping
    public R update(@RequestBody Book book){

        return new R(bookService.updateById(book));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){

        return new R(bookService.removeById(id));
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){

        return new R(true, bookService.getById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Book book){
        IPage<Book> page = bookService.getPage(currentPage, pageSize, book);
        if(currentPage > page.getPages()){
            page =bookService.getPage((int)page.getPages(), pageSize, book);
        }
        return new R(true, page);
    }
}
