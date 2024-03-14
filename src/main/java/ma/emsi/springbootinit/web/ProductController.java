package ma.emsi.springbootinit.web;

import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ServiceProduct serviceProduct;
    @GetMapping("/products")
    List<Product> getProducts(){
        return serviceProduct.getProducts();
    }

    //A compléter!
}
