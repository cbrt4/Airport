package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "flight", schema = "airport")
public class FlightEntity {

    private int id;
    private LocalDate date;
    private LocalTime time;
    private String flightNumber;
    private byte directionType;
    private String waypoint;
    private String terminal;
    private int gate;
    private int boardId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Basic
    @Column(name = "time")
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Basic
    @Column(name = "flightNumber")
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Basic
    @Column(name = "directionType")
    public byte getDirectionType() {
        return directionType;
    }

    public void setDirectionType(byte directionType) {
        this.directionType = directionType;
    }

    @Basic
    @Column(name = "waypoint")
    public String getWaypoint() {
        return waypoint;
    }

    public void setWaypoint(String waypoint) {
        this.waypoint = waypoint;
    }

    @Basic
    @Column(name = "terminal")
    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Basic
    @Column(name = "gate")
    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    @Basic
    @Column(name = "boardId")
    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof FlightEntity)) return false;

        FlightEntity that = (FlightEntity) object;

        if (getId() != that.getId()) return false;
        if (getDirectionType() != that.getDirectionType()) return false;
        if (getBoardId() != that.getBoardId()) return false;
        if (getGate() != that.getGate()) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (!getTime().equals(that.getTime())) return false;
        if (!getFlightNumber().equals(that.getFlightNumber())) return false;
        if (!getWaypoint().equals(that.getWaypoint())) return false;
        return getTerminal().equals(that.getTerminal());
    }

    @Override
    public int hashCode() {
        int result = getDate().hashCode();
        result = 31 * result + getTime().hashCode();
        result = 31 * result + getId();
        result = 31 * result + getFlightNumber().hashCode();
        result = 31 * result + (int) getDirectionType();
        result = 31 * result + getWaypoint().hashCode();
        result = 31 * result + getTerminal().hashCode();
        result = 31 * result + getGate();
        result = 31 * result + getBoardId();
        return result;
    }

    @Override
    public String toString() {
        return "\nid=" + id +
                "\ndate: " + date +
                "\ntime: " + time +
                "\nflightNumber: " + flightNumber +
                "\ndirectionType: " + directionType +
                "\nwaypoint: " + waypoint +
                "\nterminal: " + terminal +
                "\ngate: " + gate +
                "\nboardId: " + boardId;
    }
}
