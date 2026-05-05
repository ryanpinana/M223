package ch.samt.videogames.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", sequenceName = "player_seq", allocationSize = 1)
    public Long id;

    public String name;

    public Integer age;

    public String nationality;

    @OneToOne
    public GameProfile gameprofile;

    @ManyToOne
    public Team team;

    public Boolean deleted = Boolean.FALSE;
}
