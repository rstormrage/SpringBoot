package com.itheima.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.Entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDao extends BaseMapper<Book> {

}
