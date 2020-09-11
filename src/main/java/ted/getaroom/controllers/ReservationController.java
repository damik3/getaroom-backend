package ted.getaroom.controllers;

import org.springframework.web.bind.annotation.*;
import ted.getaroom.models.Reservation;
import ted.getaroom.Search;
import ted.getaroom.models.Room;
import ted.getaroom.models.User;
import ted.getaroom.myExceptions.BadRequestException;
import ted.getaroom.repositories.ReservationRepository;
import ted.getaroom.repositories.RoomRepository;
import ted.getaroom.repositories.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final Search search;

    public ReservationController(UserRepository userRepository, RoomRepository roomRepository, ReservationRepository reservationRepository, Search search) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
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

    @GetMapping("/user/{id}")
    public Iterable<Reservation> userReservations(@PathVariable Long id) {
        User user = this.userRepository.findById(id).orElseThrow(BadRequestException::new);
        return this.reservationRepository.findByUser(user);
    }

    @GetMapping("/room/{id}")
    public Iterable<Reservation> roomReservations(@PathVariable Long id) {
        Room room = this.roomRepository.findById(id).orElseThrow(BadRequestException::new);
        return this.reservationRepository.findByRoom(room);
    }
}
