package ted.getaroom;

import org.springframework.stereotype.Component;
import ted.getaroom.models.Reservation;
import ted.getaroom.repositories.ReservationRepository;

import java.util.List;

@Component
public class Search {

    private final ReservationRepository reservationRepository;

    public Search(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean isValid(Reservation reservation) {

        //
        // Check if reservation is valid and can be made
        //

        if (reservation.getDateTo().isBefore(reservation.getDateFrom()))
            return false;

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

        return r0.isEmpty() && r1.isEmpty() && r2.isEmpty() && r3.isEmpty();
    }
}
