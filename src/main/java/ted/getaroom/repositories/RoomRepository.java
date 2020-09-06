package ted.getaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ted.getaroom.models.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByCountryAndCityAndAreaAndNumBeds(String country,
                                                     String city,
                                                     String area,
                                                     int numBeds);

    List<Room> findByCountryAndCityAndNumBeds(String country,
                                              String city,
                                              int numBeds);

    List<Room> findByCountryAndAreaAndNumBeds(String country,
                                              String area,
                                              int numBeds);

    List<Room> findByCountryAndNumBeds(String country,
                                       int numBeds);
}
