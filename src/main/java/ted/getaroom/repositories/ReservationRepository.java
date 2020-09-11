package ted.getaroom.repositories;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ted.getaroom.models.Reservation;
import ted.getaroom.models.Room;
import ted.getaroom.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomAndDateFromAndDateTo(Room room,
                                                     LocalDate d1,
                                                     LocalDate d2);

    List<Reservation> findByRoomAndDateFromBetween(Room room,
                                                   LocalDate d1,
                                                   LocalDate d2);

    List<Reservation> findByRoomAndDateToBetween(Room room,
                                                   LocalDate d1,
                                                   LocalDate d2);

    List<Reservation> findByRoomAndDateFromBeforeAndDateToAfter(Room room,
                                                                LocalDate d1,
                                                                LocalDate d2);

    List<Reservation> findByUser(User user);

    List<Reservation> findByRoom(Room room);
}
