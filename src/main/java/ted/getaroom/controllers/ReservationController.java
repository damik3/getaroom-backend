package ted.getaroom.controllers;

import org.springframework.web.bind.annotation.*;
import ted.getaroom.models.Reservation;
import ted.getaroom.Search;
import ted.getaroom.myExceptions.BadRequestException;
import ted.getaroom.repositories.ReservationRepository;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;

    private final Search search;

    public ReservationController(ReservationRepository reservationRepository, Search search) {
        this.reservationRepository = reservationRepository;
        this.search = search;
    }

    @GetMapping
    public Iterable<Reservation> all() {
        return reservationRepository.findAll();
    }

    @PostMapping
    public Reservation newReservation(@RequestBody Reservation reservation ) {

        // Check if reservation is valid and can be made
        if (search.isValid(reservation)) {
            reservation.getRoom().getReservations().add(reservation);
            return reservationRepository.save(reservation);
        }
        else
            throw new BadRequestException();
    }
}
