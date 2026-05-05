package ch.samt.Customers.controller;

import ch.samt.Customers.data.CustomerRepository;
import ch.samt.Customers.model.Customer;
import ch.samt.Customers.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("customer", new Customer());
        return "home";
    }

    @GetMapping("/customers")
    public String loadCustomers(Model model, @RequestParam(value = "id", required = false) Long customerId) {
        if (customerId == null) {
            model.addAttribute("customers", customerService.findAll());
        }else{
//            Customer customer = customers.stream()
//                    .filter(c -> c.getId().equals(customerId))
//                    .findFirst().orElse(null);
//            model.addAttribute("customers", customer);
            model.addAttribute("customers", customerService.findById(customerId));
        }
        return "customerList";
    }

    @GetMapping("/customers/{surnameToFilter}")
    public String loadBySurname(@PathVariable String surnameToFilter, Model model) {
        model.addAttribute("customers", customerService.findBySurname(surnameToFilter));
        return "customerList";
    }

//    @GetMapping("/customersbycity")
//    public String loadByCity(Model model, @RequestParam(value = "city", required = true) String city) {
//        model.addAttribute("customers", customerService.findByCity(city));
//        return "customerList";
//    }

    @GetMapping("/edit/{customerId}")
    public String loadEditPage(@PathVariable Long customerId,@ModelAttribute Customer customer, Model model) {
        Customer customerToEdit = customerService.findById(customerId);
        model.addAttribute("customer", customerToEdit);
        return "home";
    }

    @PostMapping("/edit/{customerId}")
    public String updateCustomer(@PathVariable Long customerId, @Valid Customer customer, Errors errors) {
        customer.setId(customerId);
        if (errors.hasErrors()) {
            return "home";
        }
        customerService.save(customer);
        return "redirect:/customers";
    }

    @PostMapping("/insert")
    public String insert(@Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return "home";
        }
        //customers.add(customer);
        customerService.save(customer);
        return "redirect:/";
    }
}
