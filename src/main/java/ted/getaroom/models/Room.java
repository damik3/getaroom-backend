package ted.getaroom.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties({"name", "surname", "email", "phone", "rooms"})
    private User owner;

    private Integer pricePerDay;
    private String address;
    private String description;
    private Integer numBeds;

    @OneToMany(mappedBy = "room")
    @JsonIgnoreProperties({"room", "id", "user", "price"})
    Set<Reservation> reservations;

    public Room() {
    }

    public Room(Long id, @NotNull User owner, Integer pricePerDay, String address, String description, Integer numBeds, Set<Reservation> reservations) {
        this.id = id;
        this.owner = owner;
        this.pricePerDay = pricePerDay;
        this.address = address;
        this.description = description;
        this.numBeds = numBeds;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(Integer numBeds) {
        this.numBeds = numBeds;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
