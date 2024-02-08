package com.maxi.ecommerce.services.category;

import java.util.List;

import com.maxi.ecommerce.models.Category;

public interface CategoryService {

    public Category findCAtegoryById(Long id);

    public Category createCategory(Category category);

    public List<Category> findAllCategories();
}
