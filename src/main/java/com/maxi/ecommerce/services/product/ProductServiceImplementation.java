package com.maxi.ecommerce.services.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxi.ecommerce.models.Category;
import com.maxi.ecommerce.models.Product;
import com.maxi.ecommerce.repositories.ProductRepository;
import com.maxi.ecommerce.services.category.CategoryServiceImplementation;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryServiceImplementation categoryService;

    @Override
    public Product findPdoructById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product id not found"));

    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> productsList = productRepository.findAllProduct();
        if (productsList.size() == 0) {
            throw new RuntimeException("No present products to show");
        }
        return productsList;
    }

    @Override
    public List<Product> findProductByCategory(Long categoryId) {
        Category categoryDB = categoryService.findCAtegoryById(categoryId);
        List<Product> productsList = productRepository.findByCategory(categoryDB);
        if (productsList.size() == 0) {
            throw new RuntimeException("No product present for this category [" + categoryDB.getName() + "] to show");
        }
        return productsList;
    }

    private boolean existProductByName(String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            return true;
        }
        return false;
    }

    @Override
    public Product createProduct(Product product) {
        if (this.existProductByName(product.getName())) {
            return product;
        }
        Product newProduct = new Product();
        newProduct.setId(null);
        newProduct.setCategory(product.getCategory());
        newProduct.setName(product.getName());
        newProduct.setDesctiotion(product.getDesctiotion());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());
        productRepository.save(newProduct);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productDB = this.findPdoructById(id);
        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());
        productDB.setDesctiotion(product.getDesctiotion());
        productDB.setCategory(product.getCategory());
        productRepository.save(productDB);

        return productDB;
    }

    @Override
    public Product updateStock(Long id, Integer quantity) {
        Product product = this.findPdoructById(id);
        if (quantity <= 0) {
            throw new RuntimeException("You have to introduice a positive value!");
        }
        Integer stock = product.getStock() + quantity;
        product.setStock(stock);
        productRepository.save(product);

        return product;
    }

    @Override
    public void substractStock(Long id, Integer quantity) {
        Product product = this.findPdoructById(id);
        if (product.getStock() < quantity) {
            throw new RuntimeException(
                    "The stock is not available to substract this value: [" + product.getStock() + "] available");
        }
        Integer stock = product.getStock() - quantity;
        product.setStock(stock);
        productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = this.findPdoructById(id);
        productRepository.save(product);

        return product;
    }

}
