package entities;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "flight", schema = "airport")
public class FlightEntity {
    private int id;
    private String flightNumber;
    private byte directionType;
    private String leavingFrom;
    private String arrivalTo;
    private LocalTime leavingTime;
    private LocalTime arrivalTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "flight_number")
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Basic
    @Column(name = "direction_type")
    public byte getDirectionType() {
        return directionType;
    }

    public void setDirectionType(byte directionType) {
        this.directionType = directionType;
    }

    @Basic
    @Column(name = "leaving_from")
    public String getLeavingFrom() {
        return leavingFrom;
    }

    public void setLeavingFrom(String leavingFrom) {
        this.leavingFrom = leavingFrom;
    }

    @Basic
    @Column(name = "arrival_to")
    public String getArrivalTo() {
        return arrivalTo;
    }

    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    @Basic
    @Column(name = "leaving_time")
    public LocalTime getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(LocalTime leavingTime) {
        this.leavingTime = leavingTime;
    }

    @Basic
    @Column(name = "arrival_time")
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightEntity that = (FlightEntity) o;

        return id == that.id &&
                directionType == that.directionType &&
                (flightNumber != null ? flightNumber.equals(that.flightNumber) : that.flightNumber == null) &&
                (leavingFrom != null ? leavingFrom.equals(that.leavingFrom) : that.leavingFrom == null) &&
                (arrivalTo != null ? arrivalTo.equals(that.arrivalTo) : that.arrivalTo == null) &&
                (leavingTime != null ? leavingTime.equals(that.leavingTime) : that.leavingTime == null) &&
                (arrivalTime != null ? arrivalTime.equals(that.arrivalTime) : that.arrivalTime == null);
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFlightNumber().hashCode();
        result = 31 * result + (int) getDirectionType();
        result = 31 * result + getLeavingFrom().hashCode();
        result = 31 * result + getArrivalTo().hashCode();
        result = 31 * result + getLeavingTime().hashCode();
        result = 31 * result + getArrivalTime().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nid = " + id +
                "\nflightNumber = " + flightNumber +
                "\ndirectionType = " + (directionType == 1 ? "arriving" : "leaving") +
                "\nleavingFrom = " + leavingFrom +
                "\narrivalTo = " + arrivalTo +
                "\nleavingTime = " + leavingTime +
                "\narrivalTime = " + arrivalTime +
                "\n";
    }
}
