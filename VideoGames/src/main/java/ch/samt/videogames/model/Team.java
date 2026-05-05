package ch.samt.videogames.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
    @SequenceGenerator(name = "team_seq", sequenceName = "team_seq", allocationSize = 1)
    public Long id;

    public String name;

    public String nationality;

    @OneToMany
    public List<Player> players;

    public Boolean deleted = Boolean.FALSE;
}
