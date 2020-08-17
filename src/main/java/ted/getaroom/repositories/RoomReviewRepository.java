package ted.getaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ted.getaroom.models.RoomReview;

@Repository
public interface RoomReviewRepository extends JpaRepository<RoomReview, Long> {
}
