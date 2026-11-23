package school.voronin.digitalshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import school.voronin.digitalshop.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products-all";
    }



}
