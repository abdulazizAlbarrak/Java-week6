package com.example.week6hw28.Controller;

import com.example.week6hw28.Model.Product;
import com.example.week6hw28.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }
    @GetMapping("/get/{productId}")
    public ResponseEntity getProductById(@PathVariable Integer productId){
        return ResponseEntity.status(200).body(productService.getProductById(productId));
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("product added");
    }
    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(@PathVariable Integer productId,@Valid @RequestBody Product product){
        productService.updateProduct(productId, product);
        return ResponseEntity.status(200).body("Product updated");
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(200).body("Product deleted");
    }

}
