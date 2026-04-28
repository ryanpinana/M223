package ch.samt.gardenwarehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
    public Long id;

    @NotBlank
    @Pattern(regexp = "^[a-z]{3}-?([0-9]{2})$", message = "Codice non valido")
    public String code;

    @NotBlank
    public String type;

    @NotBlank
    public String name;

    @NotNull
    public Double price;

    @NotNull
    public Integer itemCount;
}
