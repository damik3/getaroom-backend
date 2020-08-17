package ted.getaroom.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ted.getaroom.models.RoomReview;
import ted.getaroom.repositories.RoomReviewRepository;

@RestController
@RequestMapping("/api/room-reviews")
public class RoomReviewController {

    private final RoomReviewRepository roomReviewRepository;

    public RoomReviewController(RoomReviewRepository roomReviewRepository) {
        this.roomReviewRepository = roomReviewRepository;
    }

    @GetMapping
    public Iterable<RoomReview> all() {
        return roomReviewRepository.findAll();
    }
}
