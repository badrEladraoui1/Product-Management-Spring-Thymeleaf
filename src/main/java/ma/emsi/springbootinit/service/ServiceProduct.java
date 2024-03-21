package ma.emsi.springbootinit.service;

import ma.emsi.springbootinit.entities.Product;

import java.util.List;

public interface ServiceProduct {
    List<Product> getProducts();
    Product getProduct(Long id);
    Product getProduct(String ref);
    Product getProductName(String name);
    Product addProduct(Product product);
    Product editProductPrice(Long id, Float price);
    void deleteProduct(Long id);
    Product deleteProduct(String name);


}
