package com.example.week6hw28.Service;

import com.example.week6hw28.ApiException.ApiException;
import com.example.week6hw28.Model.MyUser;
import com.example.week6hw28.Model.Order;
import com.example.week6hw28.Model.Product;
import com.example.week6hw28.Repository.MyUserRepository;
import com.example.week6hw28.Repository.OrderRepository;
import com.example.week6hw28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MyUserRepository myUserRepository;

    public Set<Order> getAllOrders(Integer userId){
        return orderRepository.findOrdersByMyUserId(userId);
    }
    public void addOrder(Integer userId ,Integer productId,Order order){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        Product product = productRepository.findProductById(productId);
        if (myUser==null)
            throw new ApiException("user not found");
        if (product==null)
            throw new ApiException("no product was found");
        order.setTotalPrice(order.getQuantity()*product.getPrice());
        order.setStatus("new");
        order.setMyUser(myUser);
        order.setProduct(product);
        orderRepository.save(order);
    }
    public void updateOrder(Integer userId,Integer orderId, Order order){
        MyUser myUser=myUserRepository.findMyUserById(userId);
        Order old = orderRepository.findOrderById(orderId);
        if (old==null)
            throw new ApiException("Order not found");
        if (old.getMyUser().getId()!= myUser.getId())
            throw new ApiException("not authorized");
        old.setQuantity(order.getQuantity());
        old.setDateReceived(order.getDateReceived());
        old.setTotalPrice(old.getProduct().getPrice()*order.getQuantity());
        old.setStatus("new");
        orderRepository.save(old);
    }
    public void deleteOrder(Integer userId, Integer orderId){
        Order order=orderRepository.findOrderById(orderId);
        MyUser myUser=myUserRepository.findMyUserById(userId);
        if (order==null)
            throw new ApiException("order not found");
        if (myUser.getId()!=order.getMyUser().getId())
            throw new ApiException("not authorized");
        if (order.getStatus()=="inProgress")
            throw new ApiException("order is in progress and cannot be deleted");
        if (order.getStatus()=="completed")
            throw new ApiException("order is completed and cannot be deleted");
        orderRepository.delete(order);
    }
    public void orderStatusChange(String status,Integer orderId){
        Order order=orderRepository.findOrderById(orderId);
        if (order==null)
            throw new ApiException("order not found");
        order.setStatus(status);
        orderRepository.save(order);
    }
    public Order getOrder(Integer userId, Integer orderId){
        MyUser myUser=myUserRepository.findMyUserById(userId);
        Order order=orderRepository.findOrderById(orderId);
        if (order==null)
            throw new ApiException("order not found");
        if (myUser.getId()!=order.getMyUser().getId())
            throw new ApiException("not authorized");
        return order;
    }

}
