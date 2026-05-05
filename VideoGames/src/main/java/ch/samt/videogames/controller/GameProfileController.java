package ch.samt.videogames.controller;

import ch.samt.videogames.model.GameProfile;
import ch.samt.videogames.service.GameProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameProfileController {
    private GameProfileService gameProfileService;

    @Autowired
    public GameProfileController(GameProfileService gameProfileService) {
        this.gameProfileService = gameProfileService;
    }

    @GetMapping("/gameProfile/insert")
    public String loadForm(Model model) {
        model.addAttribute("gameProfile", new GameProfile());
        return "formGameProfile";
    }

    @GetMapping("/gameProfile/list")
    public String loadProfiles(Model model) {
        model.addAttribute("profiles", gameProfileService.findAll());
        return "listGameProfiles";
    }

    @PostMapping("/gameProfile/insert")
    public String saveProfile(@ModelAttribute("gameProfile") GameProfile gameProfile, Errors errors) {
        if (errors.hasErrors()) {
            return "formGameProfile";
        }

        gameProfileService.saveGameProfile(gameProfile);
        return "redirect:/gameProfile/list";
    }
}
