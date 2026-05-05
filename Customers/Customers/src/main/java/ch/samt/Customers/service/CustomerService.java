package ch.samt.Customers.service;

import ch.samt.Customers.data.CustomerRepository;
import ch.samt.Customers.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> findBySurname(String surname) {
        return customerRepository.findBySurname(surname);
    }

//    public List<Customer> findByCity(String city) {
//        return customerRepository.findByCity(city);
//    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
