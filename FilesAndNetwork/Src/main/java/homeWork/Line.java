package homeWork;

import com.solidfire.gson.Gson;
import com.solidfire.gson.GsonBuilder;
import com.solidfire.gson.JsonArray;
import com.solidfire.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Line {
    private String lineNumber;
    private String lineName;
    private List<Station> stations;

    public Line(String lineNumber) {
        this.lineNumber = lineNumber;
        this.stations = new ArrayList<>();
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public String getLineName() {

        return lineName;
    }
}

