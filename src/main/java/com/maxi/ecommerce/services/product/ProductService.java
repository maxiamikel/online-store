package com.maxi.ecommerce.services.product;

import java.util.List;

import com.maxi.ecommerce.models.Product;

public interface ProductService {

    public Product findPdoructById(Long id);

    public List<Product> findAllProducts();

    public List<Product> findProductByCategory(Long categoryId);

    public Product createProduct(Product product);

    public Product updateProduct(Long id, Product product);

    public Product updateStock(Long id, Integer quantity);

    public void substractStock(Long id, Integer quantity);

    public Product deleteProduct(Long id);
}
