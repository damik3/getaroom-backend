package ted.getaroom.payload.request;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class SearchRequest {

    @NotBlank
    @NotNull
    private String country;

    private String city;

    private String area;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @NotNull
    private int numBeds;

    public SearchRequest(@NotBlank @NotNull String country, @NotBlank String city, @NotBlank String area, @NotNull LocalDate dateFrom, @NotNull LocalDate dateTo, @NotNull int numBeds) {
        this.country = country;
        this.city = city;
        this.area = area;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.numBeds = numBeds;
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

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", numBeds=" + numBeds +
                '}';
    }
}
