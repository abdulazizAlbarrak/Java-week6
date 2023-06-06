package com.example.week6hw28.Controller;

import com.example.week6hw28.Model.MyUser;
import com.example.week6hw28.Model.Order;
import com.example.week6hw28.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(orderService.getAllOrders(myUser.getId()));
    }
    @PostMapping("/add/{productId}")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer productId, @Valid @RequestBody Order order){
        orderService.addOrder(myUser.getId(), productId,order);
        return ResponseEntity.status(200).body("order added");
    }
    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody Order order,@PathVariable Integer orderId){
        orderService.updateOrder(myUser.getId(), orderId, order);
        return ResponseEntity.status(200).body("order updated");
    }
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId){
        orderService.deleteOrder(myUser.getId(), orderId);
        return ResponseEntity.status(200).body("order deleted");
    }
    @PutMapping("/status/{orderId}/{status}")
    public ResponseEntity statusChange(@PathVariable String status, @AuthenticationPrincipal MyUser myUser,@PathVariable Integer orderId){
        orderService.orderStatusChange(status, orderId);
        return ResponseEntity.status(200).body("status changed");
    }
    @GetMapping("/get-order/{orderId}")
    public ResponseEntity getOrderById(@PathVariable Integer orderId, @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(orderService.getOrder(myUser.getId(), orderId));
    }
}
