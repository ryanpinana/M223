package ch.samt.gardenwarehouse;


import ch.samt.gardenwarehouse.data.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc  // Abilita l'uso di MockMvc che ci permette di simulare le chiamate HTTP (GET, POST, ecc..)
@SpringBootTest  // Avvia l'applicazione Spring con tutte le sue componenti
@Transactional
public class GardenWarehouseControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testLoadItems() throws Exception {
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(view().name("items"))
                .andExpect(model().attributeExists("items"));
    }

    @Test
    public void testLoadItemsByCode() throws Exception {
        mockMvc.perform(get("/items/ala-01"))
                .andExpect(status().isOk())
                .andExpect(view().name("items"))
                .andExpect(model().attributeExists("items"))
                .andExpect(content().string(containsString("Lattuce")));
    }

    @Test
    public void testSellItem() throws Exception {
        mockMvc.perform(get("/items/sell")
        .param("code", "ala-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items/ala-01"));
    }

    @Test
    public void testAddItem() throws Exception {
        mockMvc.perform(get("/items/add")
        .param("code", "ala-01")
        .param("number", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items/ala-01"));
    }

    @Test
    public void testLoadInsertForm() throws Exception {
        mockMvc.perform(get("/items/insert"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeExists("item"));
    }

    @Test
    public void testSaveItem() throws Exception {
        mockMvc.perform(post("/items/insert"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items"));
    }
}
