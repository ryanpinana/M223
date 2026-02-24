package ch.samt.esercizio2;
import ch.samt.esercizio2.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.*;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //Test GET /products
    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(content().string(containsString("Latte")))
                .andExpect(content().string(containsString("Sushi Teido")));
    }

    //Test filtro per nome
    @Test
    void testGetProductsByName() throws Exception {
        mockMvc.perform(get("/products")
                        .param("name", "Latte"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(content().string(containsString("Latte")));
    }

    //Test filtro per prezzo
    @Test
    void testGetProductsByPrice() throws Exception {
        mockMvc.perform(get("/products")
                        .param("pricelessthan", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(content().string(containsString("Latte")));
    }

    //Test GET /newproduct
    @Test
    void testNewProductGet() throws Exception {
        mockMvc.perform(get("/newproduct"))
                .andExpect(status().isOk())
                .andExpect(view().name("newproduct"));
    }

    //Test POST /newproduct
    @Test
    void testNewProductPost() throws Exception {
        mockMvc.perform(post("/newproduct")
                        .param("id", "10")
                        .param("name", "Felpa")
                        .param("price", "49.9")
                        .param("description", "Felpa in cotone"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }
}