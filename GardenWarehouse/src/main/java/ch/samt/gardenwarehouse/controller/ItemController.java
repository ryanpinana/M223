package ch.samt.gardenwarehouse.controller;

import ch.samt.gardenwarehouse.model.Item;
import ch.samt.gardenwarehouse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {
    protected ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String showItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "items";
    }

    @GetMapping("/items/{code}")
    public String showItemByCode(@PathVariable String code, Model model) {
        model.addAttribute("items", itemService.findByCode(code));
        return "items";
    }

    @GetMapping("/items/sell")
    public String sellItem(@RequestParam(value = "code", required = true) String code, Model model) {
        model.addAttribute("items", itemService.sellItem(code));
        return "redirect:/items/" + code;
    }

    @GetMapping("/items/add")
    public String addItem(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "number", required = true) Integer number, Model model) {
        model.addAttribute("items", itemService.addItem(code, number));
        return "redirect:/items/" + code;
    }

    @GetMapping("/items/insert")
    public String loadForm(Model model) {
        model.addAttribute("item", new Item());
        return "form";
    }

    @PostMapping("/items/insert")
    public String saveItem(@ModelAttribute("item") Item item, Errors errors) {
        if (errors.hasErrors()) {
            return "form";
        }

        itemService.saveItem(item);
        return "redirect:/items";
    }

}
