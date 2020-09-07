package ted.getaroom.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ted.getaroom.models.Reservation;
import ted.getaroom.models.Room;
import ted.getaroom.myExceptions.BadRequestException;
import ted.getaroom.payload.request.SearchRequest;
import ted.getaroom.repositories.RoomRepository;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;



@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final RoomRepository roomRepository;

    public SearchController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }



    @PostMapping
    public Iterable<Room> search(@Valid @RequestBody SearchRequest searchRequest) {

        // Check validity of dates
        if (!searchRequest.getDateTo().isAfter(searchRequest.getDateFrom()))
            throw new BadRequestException();



        List<Room> rooms = new LinkedList<Room>();

        //
        // Find rooms based on location and number of beds
        //

        if (searchRequest.getCity().isEmpty() && searchRequest.getArea().isEmpty())
            rooms.addAll(roomRepository.findByCountryAndNumBeds(searchRequest.getCountry(),
                    searchRequest.getNumBeds()));

        else if (searchRequest.getArea().isEmpty())
            rooms.addAll(roomRepository.findByCountryAndCityAndNumBeds(searchRequest.getCountry(),
                    searchRequest.getCity(),
                    searchRequest.getNumBeds()));

        else //if (searchRequest.getCity().isEmpty())
            rooms.addAll(roomRepository.findByCountryAndAreaAndNumBeds(searchRequest.getCountry(),
                    searchRequest.getArea(),
                    searchRequest.getNumBeds()));



        List<Room> result = new LinkedList<Room>(rooms);

        //
        // For each room satisfying the above
        //

        for (Room room: rooms) {
            Set<Reservation> roomReservations = room.getReservations();

            //
            // For each reservation made on current room, check if it available for the wanter period
            //

            for (Reservation reservation: roomReservations) {

                // If current reservation is ok, continue
                if (searchRequest.getDateTo().isBefore(reservation.getDateFrom()) ||
                    searchRequest.getDateFrom().isAfter(reservation.getDateTo()))
                    continue;

                // If current reservation is not ok, remove item from result and check next room
                else {
                    result.remove(room);
                    break;
                }
            }
        }



        return result;
    }
}
