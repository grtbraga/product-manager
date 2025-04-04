package com.myproject.product_manager.repository;
import com.myproject.product_manager.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryPath(String categoryPath, Sort sort);
    List<Product> findByAvailable(Boolean available, Sort sort);
    List<Product> findByCategoryPathAndAvailable(String categoryPath, Boolean available, Sort sort);
    @Query("SELECT DISTINCT p.categoryPath FROM Product p WHERE p.categoryPath IS NOT NULL")
    List<String> findDistinctCategoryPaths();
}