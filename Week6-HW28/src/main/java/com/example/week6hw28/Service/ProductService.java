package com.example.week6hw28.Service;

import com.example.week6hw28.ApiException.ApiException;
import com.example.week6hw28.Model.Product;
import com.example.week6hw28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }
    public void updateProduct(Integer productId,Product product){
        Product old = productRepository.findProductById(productId);
        if (old==null)
            throw new ApiException("Product not found");
        old.setName(product.getName());
        old.setPrice(product.getPrice());
        productRepository.save(old);
    }
    public void deleteProductById(Integer productId){
        Product product = productRepository.findProductById(productId);
        if (product==null)
            throw new ApiException("Product not found, wrong id");
        productRepository.delete(product);
    }
    public Product getProductById(Integer productId){
        Product product = productRepository.findProductById(productId);
        if (product==null)
            throw new ApiException("Product not found, try another id");
        return product;
    }

}
