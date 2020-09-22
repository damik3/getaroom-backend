package ted.getaroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ted.getaroom.models.FileInfo;
import ted.getaroom.models.Room;
import ted.getaroom.models.User;
import ted.getaroom.payload.response.MessageResponse;
import ted.getaroom.repositories.RoomRepository;
import ted.getaroom.myExceptions.BadRequestException;
import ted.getaroom.repositories.UserRepository;

import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private UserRepository userRepository;

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }



    @GetMapping
    public Iterable<Room> all() {
        return roomRepository.findAll();
    }



    @PostMapping
    public Room newRoom(@RequestBody Room newRoom) {

        // Field owner.id must not be null
        if (newRoom.getOwner().getId() == null)
            throw new BadRequestException();

        // Find owner and add room to his ownership
        User user = userRepository.findById(newRoom.getOwner().getId()).orElse(null);

        if (user == null)
            throw new BadRequestException();

        Set<Room> rooms = user.getRooms();
        rooms.add(newRoom);
        user.setRooms(rooms);

        // Save newRoom and return
        return roomRepository.save(newRoom);
    }



    @PostMapping("/{id}/add-main-photo")
    public ResponseEntity<MessageResponse> addMainPhoto(@PathVariable Long id, @RequestBody FileInfo photo) {
        Room room = roomRepository.findById(id).orElse(null);

        if (room == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Room not found!"));
        }
        else {
            room.setMainPhotoUrl(photo.getUrl());
            roomRepository.save(room);
            return ResponseEntity.ok(new MessageResponse("Main photo added!"));
        }
    }



    @GetMapping("/{id}")
    public Room one(@PathVariable Long id) {
        return roomRepository.findById(id)
                .orElse(new Room());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {

        // Find room
        Room room = roomRepository.findById(id).orElse(null);
        if (room == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Room does not exist!"));
        }

        // Find owner
        User owner = userRepository.findById(room.getOwner().getId()).orElse(null);
        if (owner == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Room does not have an owner!"));
        }

        // Remove room from owner
        owner.getRooms().remove(room);
        userRepository.save(owner);

        // Delete room
        roomRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("Room deleted successfully!"));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@RequestBody Room newRoom, @PathVariable Long id) {

        // Get room
        Room room = roomRepository.findById(id).orElse(null);
        if (room == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Room does not exist!"));
        }

        // Update room
        room.setAddress(newRoom.getAddress());
        room.setArea(newRoom.getArea());
        room.setCity(newRoom.getCity());
        room.setCountry(newRoom.getCountry());
        room.setDescription(newRoom.getDescription());
        room.setNumBeds(newRoom.getNumBeds());
        room.setPricePerDay(newRoom.getPricePerDay());
        room.setTitle(newRoom.getTitle());

        // Persist room
        roomRepository.save(room);

        return ResponseEntity.ok(new MessageResponse("Room successfully updated!"));

    }
}
