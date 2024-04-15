package homeWork;

import java.time.LocalDate;
import java.util.Objects;

public class Station {

    private String stationName;
    private String depth;
    private LocalDate date;
    private boolean hasConnection;

    public Station(String stationName) {
        this.stationName = stationName;
    }

    public Station(String stationName, String depth) {
        this.stationName = stationName;
        this.depth = depth;
    }

    public Station(String stationName, LocalDate date) {
        this.stationName = stationName;
        this.date = date;
    }

    public String getStationName() {
        return stationName;
    }

    public String getDepth() {
        return depth;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        if (depth != null) {
            return  stationName + " "  + depth;
        } else if (date != null) {
            return stationName + " "  + date;
        } else {
            return  stationName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(stationName, station.stationName) &&
                Objects.equals(depth, station.depth) &&
                Objects.equals(date, station.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, depth, date);
    }

    public boolean hasConnection() {
        return hasConnection;
    }
}

