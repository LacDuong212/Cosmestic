//22110304 - Võ Nguyễn Hòa Lạc Dương
package com.api.cosmesticapi.repository;

import com.api.cosmesticapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
