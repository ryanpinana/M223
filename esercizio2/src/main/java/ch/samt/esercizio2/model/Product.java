package ch.samt.esercizio2.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @NotNull(message = "Il campo è obbligatorio")
    private int id;

    @NotBlank(message = "Il campo è obbligatorio")
    @Size(min = 2, max = 100, message = "Il nome deve essere da 2 a 100 caratteri")
    private String name;

    @NotNull(message = "Il campo è obbligatorio")
    @Positive(message = "Non può essere negativo")
    private double price;

    @FutureOrPresent(message = "Data di scadenza già superata")
    private LocalDate expirationDate;

    @NotBlank(message = "Il campo è obbligatorio")
    private String description;
}
