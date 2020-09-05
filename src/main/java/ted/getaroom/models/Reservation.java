package ted.getaroom.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties({"name", "surname", "email", "phone", "rooms"})
    User user;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties({"owner", "pricePerDay", "address", "description", "numBeds", "reservations", "country", "city", "area"})    @NotNull
    Room room;

    @NotNull
    private int price;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    public Reservation() {
    }

    public Reservation(Long id, User user, Room room, int price, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.price = price;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
