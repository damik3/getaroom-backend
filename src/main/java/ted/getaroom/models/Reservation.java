package ted.getaroom.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    @EmbeddedId
    ReservationKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("room_id")
    @JoinColumn(name = "room_id")
    Room room;

    private int price;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    public Reservation() {
    }

    public Reservation(ReservationKey id, User user, Room room, int price, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.price = price;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public ReservationKey getId() {
        return id;
    }

    public void setId(ReservationKey id) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
