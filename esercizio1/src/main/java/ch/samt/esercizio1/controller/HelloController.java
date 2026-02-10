package ch.samt.esercizio1.controller;

import ch.samt.esercizio1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    public final Map<Long, User> users = new HashMap<Long, User>();
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/{name}")
    public String helloDinamico(Model model, @PathVariable("name") String name) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/user/insert")
    public String loadForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", users);
        return "form";
    }

    @GetMapping("/user/load")
    public String loadUser(Model model, @RequestParam(value = "userId", required = true) Long id) {
        User user = users.get(id);
        model.addAttribute("user", user);
        return "singleUser";
    }

    @PostMapping("/user/insert1")
    public String insertUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "redirect:/user/insert";
    }
}
