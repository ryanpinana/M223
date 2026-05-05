package ch.samt.videogames.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GameProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameprofile_seq")
    @SequenceGenerator(name = "gameprofile_seq", allocationSize = 1)
    public Long id;

    public String gamerTag;

    public Integer gamesOwned;

    public Integer elo;

    public Integer achivements;

    public Boolean deleted = Boolean.FALSE;
}
