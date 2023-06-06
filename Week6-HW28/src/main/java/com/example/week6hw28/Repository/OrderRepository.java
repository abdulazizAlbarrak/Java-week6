package com.example.week6hw28.Repository;

import com.example.week6hw28.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    public Order findOrderById(Integer orderId);
    public Set<Order> findOrdersByMyUserId(Integer userId);
}
