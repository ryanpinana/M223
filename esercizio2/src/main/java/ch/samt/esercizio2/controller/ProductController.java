package ch.samt.esercizio2.controller;

import ch.samt.esercizio2.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProductController {
    private static HashMap<Integer,Product> products = new HashMap<>();
    static {
        products.put(1, new Product(1, "Latte", 2.50, LocalDate.of(2026, 5, 10), "Latte fresco di giornata"));
        products.put(2, new Product(2, "Sushi Teido", 13.9, LocalDate.of(2026, 2, 15), "Sushi fatto a mano"));
        products.put(3, new Product(3, "Fois Grass", 25.1, LocalDate.of(2026, 6, 8), "Foi Grass francese"));
    }

    @GetMapping("/products")
    public String loadProducts(Model model, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "pricelessthan", required = false) Double priceLessThan) {

        // Nessun filtro
        if ((name == null || name.isEmpty()) && priceLessThan == null) {
            model.addAttribute("products", products);
            return "products";
        }

        HashMap<Integer, Product> filteredProducts = new HashMap<>();

        for (Product p : products.values()) {

            boolean matches = true;

            if (name != null && !name.isEmpty()) {
                if (!p.getName().toLowerCase().contains(name.toLowerCase())) {
                    matches = false;
                }
            }

            if (priceLessThan != null) {
                if (p.getPrice() >= priceLessThan) {
                    matches = false;
                }
            }

            if (matches) {
                filteredProducts.put(p.getId(), p);
            }
        }

        model.addAttribute("products", filteredProducts);
        return "products";
    }

    @GetMapping("/newproduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "newproduct";
    }

    @GetMapping("/products/show/{id}")
    public String showProduct(@PathVariable int id, Model model) {
        System.out.println(products.size());
        if(id > products.size()) {
            model.addAttribute("product", null);
            return "showproduct";
        }
        model.addAttribute("product", products.get(id));
        return "showproduct";
    }

    @PostMapping("/newproduct")
    public String saveProduct(@Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return "newproduct";
        }
        products.put(product.getId(), product);
        return "redirect:/products";
    }
}
