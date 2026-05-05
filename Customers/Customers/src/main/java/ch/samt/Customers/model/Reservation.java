package ch.samt.Customers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "reservation_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotBlank
    @Size(min = 1, max = 3, message = "Il numero di stanza deve essere tra 1 e 999")
    private String room;

    @NotBlank
    @Size(min = 8, max = 16, message = "Ora del checkIn tra 8 e 16 caratteri")
    private String checkin;

    @NotBlank
    @Size(min = 8, max = 16, message = "Ora del checkOut tra 8 e 16 caratteri")
    private String checkout;
}
