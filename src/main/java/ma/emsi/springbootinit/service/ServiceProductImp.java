package ma.emsi.springbootinit.service;

import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceProductImp implements ServiceProduct{
    @Autowired
    ProductRepo productRepo;
    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    //Le reste à compléter
}
