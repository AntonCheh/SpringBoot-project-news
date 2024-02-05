package homeWork;

import com.solidfire.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private String stationNumber;
    private String stationName;

    public Station(String stationNumber, String stationName) {
        this.stationNumber = stationNumber;
        this.stationName = stationName;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stationNumber", stationNumber);
        jsonObject.addProperty("stationName", stationName);
        return jsonObject;
    }
}



    /*
    private String stationNumber;
    private String stationName;

    public Station(String stationNumber, String stationName) {
        this.stationNumber = stationNumber;
        this.stationName = stationName;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public String getStationName() {
        return stationName;
    }


     */