package ch.samt.Customers.controller;

import ch.samt.Customers.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/customers/reservations")
    public String loadAllReservations(Model model) {
        model.addAttribute("reservations", this.reservationService.findAll());
        return "reservationList";
    }
}
