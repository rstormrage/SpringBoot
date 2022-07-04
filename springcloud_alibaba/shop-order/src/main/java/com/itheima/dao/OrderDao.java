package com.itheima.dao;


import com.itheima.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Orders, Integer> {

}
