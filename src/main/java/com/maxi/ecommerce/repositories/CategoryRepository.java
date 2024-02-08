package com.maxi.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxi.ecommerce.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String name);
}
