package ch.samt.Customers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", allocationSize = 1)
    public int id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    public String street;

    @NotBlank
    @Size(min = 1, max = 5, message = "Lunghezza consentita tra 1 e 5 caratteri")
    public String num;

    @NotBlank
    @Size(min = 4, max = 7, message = "Lunghezza consentita tra 4 e 7 caratteri")
    public String zip;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    public String city;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    public String nation;
}
