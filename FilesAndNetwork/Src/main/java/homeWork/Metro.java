package homeWork;

import com.solidfire.gson.Gson;
import com.solidfire.gson.GsonBuilder;
import com.solidfire.gson.JsonArray;
import com.solidfire.gson.JsonObject;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Metro {

    List<Line> lines;

    public Metro() {
        this.lines = new ArrayList<>();
    }

    public List<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void saveToJsonFile(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();

//        for (Line line : lines) {
//            jsonArray.add(line.toJson());
//        }

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(jsonArray, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



