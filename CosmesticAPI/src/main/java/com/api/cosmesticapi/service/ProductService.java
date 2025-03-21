//22110304 - Võ Nguyễn Hòa Lạc Dương
package com.api.cosmesticapi.service;


import com.api.cosmesticapi.entity.Product;
import com.api.cosmesticapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategoryOrderByPriceAsc(Long categoryId) {
        return productRepository.findProductsByCategoryOrderByPriceAsc(categoryId);
    }
}
