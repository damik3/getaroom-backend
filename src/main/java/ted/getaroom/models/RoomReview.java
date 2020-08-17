package ted.getaroom.models;

import javax.persistence.*;

@Entity
public class RoomReview {

    @EmbeddedId
    RoomReviewKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("room_id")
    @JoinColumn(name = "room_id")
    Room room;

    private String review;
    private int stars;

    public RoomReview() {
    }

    public RoomReview(RoomReviewKey id, User user, Room room, String review, int stars) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.review = review;
        this.stars = stars;
    }

    public RoomReviewKey getId() {
        return id;
    }

    public void setId(RoomReviewKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
