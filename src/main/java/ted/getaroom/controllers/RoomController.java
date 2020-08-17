package ted.getaroom.controllers;

import org.springframework.web.bind.annotation.*;
import ted.getaroom.models.Room;
import ted.getaroom.repositories.RoomRepository;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

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
        return roomRepository.save(newRoom);
    }

    @GetMapping("/{id}")
    public Room one(@PathVariable Long id) {
        return roomRepository.findById(id)
                .orElse(new Room());
    }
}
