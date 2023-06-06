package com.example.week6hw28.Repository;

import com.example.week6hw28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findProductById(Integer productId);
    public Product findProductByName(String productName);
}
