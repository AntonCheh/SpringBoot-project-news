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

    public Line(String lineNumber, String lineName) {
        this.lineNumber = lineNumber;
        this.lineName = lineName;
        this.stations = new ArrayList<>();
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String getLineName() {
        return lineName;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("lineNumber", lineNumber);
        jsonObject.addProperty("lineName", lineName);

        JsonArray stationsArray = new JsonArray();
        for (Station station : stations) {
            stationsArray.add(station.toJson());
        }

        jsonObject.add("stations", stationsArray);

        return jsonObject;
    }
}



