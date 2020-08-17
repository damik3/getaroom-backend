package ted.getaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ted.getaroom.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
