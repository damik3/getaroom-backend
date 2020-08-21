package ted.getaroom.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ted.getaroom.models.Reservation;
import ted.getaroom.repositories.ReservationRepository;

@RestController
@RequestMapping("/api/reservations")
//@PreAuthorize("hasRole('ADMIN')")
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public Iterable<Reservation> all() {
        return reservationRepository.findAll();
    }

    @PostMapping
    public Reservation newReservation(@RequestBody Reservation r ) {
        return reservationRepository.save(r);
    }
}
