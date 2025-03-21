package com.api.cosmesticapi.controller;

import com.api.cosmesticapi.entity.Product;
import com.api.cosmesticapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/category/{categoryId}/price-asc")
    public List<Product> getProductsByCategoryOrderByPriceAsc(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryOrderByPriceAsc(categoryId);
    }
}
