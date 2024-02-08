package com.maxi.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maxi.ecommerce.models.Category;
import com.maxi.ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p")
    public List<Product> findAllProduct();

    public Product findByName(String name);

}
