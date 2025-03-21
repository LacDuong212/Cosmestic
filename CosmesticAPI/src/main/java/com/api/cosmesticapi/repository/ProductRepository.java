//22110304 - Võ Nguyễn Hòa Lạc Dương
package com.api.cosmesticapi.repository;

import com.api.cosmesticapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId ORDER BY p.cost ASC")
    List<Product> findProductsByCategoryOrderByPriceAsc(@Param("categoryId") Long categoryId);
}
