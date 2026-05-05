package ch.samt.videogames.controller;

import ch.samt.videogames.model.Player;
import ch.samt.videogames.service.PlayerService;
import ch.samt.videogames.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayerController {
    private PlayerService playerService;
    private TeamService teamService;

    @Autowired
    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping("/player")
    public String listPlayers(Model model) {
        model.addAttribute("players", this.playerService.getAllPlayers());
        return "playerList";
    }

    @GetMapping("/player/insert")
    public String loadFormPlayer(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", this.teamService.getAllTeams());
        return "playerForm";
    }

    @PostMapping("/player/insert")
    public String savePlayer(@ModelAttribute("player") Player player, Errors errors) {
        if (errors.hasErrors()) {
            return "playerForm";
        }
        this.playerService.savePlayer(player);
        return "redirect:/player";
    }
}
