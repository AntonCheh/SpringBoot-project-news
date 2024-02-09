package homeWork;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonParsing {

    private static final String DATA_FILE = "/Users/User/Desktop/stations-data/data/2/4/depths-1.json";
    private static final String DATA_FILE2 = "/Users/User/Desktop/stations-data/data/4/6/depths-3.json";
    private static final String DATA_FILE3 = "/Users/User/Desktop/stations-data/data/7/1/depths-2.json";


//    public static void main(String[] args) {
//        List<Station> allStations = new ArrayList<>();
//
//        List<Station> stations1 = parseJsonFile(DATA_FILE);
//        allStations.addAll(stations1);
//
//        List<Station> stations2 = parseJsonFile(DATA_FILE2);
//        allStations.addAll(stations2);
//
//        List<Station> stations3 = parseJsonFile(DATA_FILE3);
//        allStations.addAll(stations3);
//
//        printStations(allStations);
//
//    }

    static List<Station> parseJsonFile (String filePath) {
        try {
            JSONParser parser = new JSONParser();
            Object jsonData = parser.parse(getJsonFile(filePath));

            if (jsonData instanceof JSONArray) {
                return parseStations((JSONArray) jsonData);
            } else {
                throw new IllegalArgumentException("Invalid JSON format: Expected JSONArray");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static List<Station> parseStations(JSONArray stationsArray) {
        List<Station> stations = new ArrayList<>();
        stationsArray.forEach(stationObject -> {
            if (stationObject instanceof JSONObject) {
                JSONObject stationJson = (JSONObject) stationObject;
                String stationName = (String) stationJson.get("station_name");
                String depth = (String) stationJson.get("depth");

                Station station = new Station(stationName, depth);
                stations.add(station);
            } else {
                throw new IllegalArgumentException("Invalid JSON format: Expected JSONObject in stationsArray");
            }
        });
        return stations;
    }

    private static String getJsonFile(String filePath) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            lines.forEach(builder::append);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    private static void printStations(List<Station> stations) {
        stations.forEach(station -> {
            System.out.println("Station Name: " + station.getStationName());
            System.out.println("Depth: " + station.getDepth());
            System.out.println();
        });
    }


        public static void print(List<String> filePaths) {
            List<Station> allStations = new ArrayList<>();

            for (String filePath : filePaths) {
                List<Station> stations = parseJsonFile(filePath);
                allStations.addAll(stations);
            }

            printStations(allStations);
        }
}


