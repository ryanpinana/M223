package ch.samt.Customers.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    public Long id;

    @NotBlank
    @Size(min = 2, max = 10, message = "Lunghezza tra 2 e 10 caratteri")
    public String name;

    @NotBlank
    @Size(min = 2, max = 10, message = "Lunghezza tra 2 e 10 caratteri")
    public String surname;

    @NotNull
    @Min(1)
    @Max(100)
    public Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    public Address address;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Reservation> reservations;
}
