package ted.getaroom.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private User owner;

    private Integer pricePerDay;
    private String address;
    private String description;
    private Integer numBeds;

    public Room() {
    }

    public Room(Long id, @NotNull User owner, Integer pricePerDay, String address, String description, Integer numBeds) {
        this.id = id;
        this.owner = owner;
        this.pricePerDay = pricePerDay;
        this.address = address;
        this.description = description;
        this.numBeds = numBeds;
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
}
