package ted.getaroom.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties({"rooms", "hostReqPending"})
    private User owner;

    private String title;
    private String country;
    private String city;
    private String area;
    private String address;
    private Integer numBeds;
    private String description;
    private Integer pricePerDay;
    private String mainPhotoUrl;

    @OneToMany(mappedBy = "room")
    @JsonIgnoreProperties({"room", "id", "user", "price", "country", "city", "area"})
    Set<Reservation> reservations = new HashSet<>();

    public Room() {
    }

    public Room(Long id, @NotNull User owner, String country, String city, String area, String title, Integer numBeds, String description, Integer pricePerDay, String address, Set<Reservation> reservations) {
        this.id = id;
        this.owner = owner;
        this.country = country;
        this.city = city;
        this.area = area;
        this.title = title;
        this.numBeds = numBeds;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.address = address;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public void setMainPhotoUrl(String mainPhotoUrl) {
        this.mainPhotoUrl = mainPhotoUrl;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", owner=" + owner +
                ", title='" + title + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", numBeds=" + numBeds +
                ", description='" + description + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
