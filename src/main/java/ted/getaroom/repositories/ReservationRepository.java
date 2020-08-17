package ted.getaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ted.getaroom.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
