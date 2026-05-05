package ch.samt.videogames.service;

import ch.samt.videogames.data.GameProfileRepository;
import ch.samt.videogames.model.GameProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameProfileService {
    private GameProfileRepository gameProfileRepository;

    @Autowired
    public GameProfileService(GameProfileRepository gameProfileRepository) {
        this.gameProfileRepository = gameProfileRepository;
    }

    public List<GameProfile> findAll() {
        return gameProfileRepository.findAll();
    }

    public void saveGameProfile(GameProfile gameProfile) {
        gameProfileRepository.save(gameProfile);
    }
}
