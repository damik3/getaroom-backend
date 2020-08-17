package ted.getaroom.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class RoomReviewKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "room_id")
    Long roomId;

    public RoomReviewKey() {
    }

    public RoomReviewKey(Long userId, Long roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}