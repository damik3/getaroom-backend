package ted.getaroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ted.getaroom.models.Reservation;
import ted.getaroom.myExceptions.BadRequestException;
import ted.getaroom.repositories.ReservationRepository;
import ted.getaroom.repositories.RoomRepository;
import ted.getaroom.repositories.UserRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
//@PreAuthorize("hasRole('ADMIN')")
public class ReservationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public Iterable<Reservation> all() {
        return reservationRepository.findAll();
    }

    @PostMapping
    public Reservation newReservation(@RequestBody Reservation reservation ) {

        //
        // Check if reservation is valid and can be made
        //

        if (reservation.getDateTo().isBefore(reservation.getDateFrom()))
            throw new BadRequestException();

        List<Reservation> r0 = reservationRepository.findByRoomAndDateFromAndDateTo(reservation.getRoom(),
                reservation.getDateFrom(),
                reservation.getDateTo());

        List<Reservation> r1  = reservationRepository.findByRoomAndDateFromBetween(reservation.getRoom(),
                reservation.getDateFrom(),
                reservation.getDateTo());

        List<Reservation> r2  = reservationRepository.findByRoomAndDateToBetween(reservation.getRoom(),
                reservation.getDateFrom(),
                reservation.getDateTo());

        List<Reservation> r3  = reservationRepository.findByRoomAndDateFromBeforeAndDateToAfter(reservation.getRoom(),
                reservation.getDateFrom(),
                reservation.getDateTo());

        // If not, throw exception
        if (!(r0.isEmpty() && r1.isEmpty() && r2.isEmpty() && r3.isEmpty()))
            throw new BadRequestException();

        // If yes, persist it
        return reservationRepository.save(reservation);
    }
}
