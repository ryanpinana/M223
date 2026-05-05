package ch.samt.Customers.data;

import ch.samt.Customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    @Query("SELECT * FROM Customer WHERE LOWER(surname) = LOWER(:surname)")
//    List<Customer> findBySurname(String surname);

    List<Customer> findBySurname(String surname);

//    @Query("SELECT * FROM Customer WHERE LOWER(city) = LOWER(:city)")
//    List<Customer> findByCity(String city);

//    List<Customer> findByCity(String city);

}
