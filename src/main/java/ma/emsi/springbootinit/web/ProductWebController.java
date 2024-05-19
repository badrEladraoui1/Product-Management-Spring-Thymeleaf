package ma.emsi.springbootinit.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.service.ServiceProduct;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductWebController {
    ServiceProduct serviceProduct;

    @GetMapping("/index")
    String getProducts(Model model,
                       @RequestParam(defaultValue = "0") int page) {
        Page<Product> productList
                = serviceProduct.getProducts(page);
        model.addAttribute("prdcts", productList);
        model.addAttribute("title", "Liste des produits");
        model.addAttribute("count", productList.getTotalElements());
        model.addAttribute("currentPage", productList.getNumber());

        int[] listPages = new int[productList.getTotalPages()];
        for (int i = 0; i < listPages.length; i++) listPages[i] = i;

        model.addAttribute("listPages", listPages);
        model.addAttribute("product", new Product());

        return "products"; //Nom de la page HTML (templates)
    }

    @GetMapping("/delete")
    String deleteProductById(@RequestParam Long id , RedirectAttributes redirectAttributes) {
        System.out.println("PRDCT TO BE DELETE: " + id);
        try {
            serviceProduct.deleteProduct(id);
            redirectAttributes.addFlashAttribute("deleted" , "deleted product successfully");
            return "redirect:/index";
        }catch (Exception exception){
            redirectAttributes.addFlashAttribute("error" , "Error while deleting product");
            return "redirect:/index";
        }
    }

    @PostMapping("/add")
        // @ModelAttribute to bind the form data to the Product object
    String addProduct(@ModelAttribute Product product ,  RedirectAttributes redirectAttributes) {
        serviceProduct.addProduct(product);
        redirectAttributes.addAttribute("page", serviceProduct.getLastPage());
        return "redirect:/index";
    }

    @PostMapping("/edit")
    String editProductByPrice(@RequestParam Float price, @RequestParam Long id) {
        serviceProduct.editProductPrice(id, price);
        return "redirect:/index";
    }


    @GetMapping("/showEditForm/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        Product product = serviceProduct.getProductById(id);
        model.addAttribute("title", "modification de produits");
        model.addAttribute("product", product);
        return "update_employee";
    }

//    @PostMapping("/updateProduct")
//    public String updateProduct(@ModelAttribute Product product) {
//        serviceProduct.updateProduct(product);
//        return "redirect:/index";
//    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute Product product , BindingResult theBindingResult) {
        Product existingProduct = serviceProduct.getProductById(product.getId());
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setRef(product.getRef());
            existingProduct.setCode(product.getCode());
            existingProduct.setProdDate(product.getProdDate());
            existingProduct.setPrice(product.getPrice());
            serviceProduct.saveProduct(existingProduct);
        }
        return "redirect:/index";

    }

    @GetMapping("/search")
    public String search(@RequestParam("term") String term, Model model , RedirectAttributes redirectAttributes) {
        List<Product> products = serviceProduct.findByNameContaining(term);
        model.addAttribute("products", products);
        model.addAttribute("title", "searched products");
        return "search_results";  // return the view that displays the search results
    }

}
