package com.api.cosmesticapi.service;

import com.api.cosmesticapi.entity.Category;
import com.api.cosmesticapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.isEmpty() ? ResponseEntity.ok("Không có category để hiển thị") : ResponseEntity.ok(categories);
    }
}
