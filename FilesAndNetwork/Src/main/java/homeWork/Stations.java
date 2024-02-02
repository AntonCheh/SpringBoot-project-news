package homeWork;

import com.solidfire.gson.Gson;
import com.solidfire.gson.GsonBuilder;
import com.solidfire.gson.JsonArray;
import com.solidfire.gson.JsonObject;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stations {
        List<Line> lines;

        public Stations() {
            this.lines = new ArrayList<>();
        }

        public void addLine(Line line) {
            lines.add(line);
        }

        public List<Line> getLines() {
            return lines;
        }

        public void saveToJsonFile(String filePath) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonArray jsonArray = new JsonArray();

            for (Line line : lines) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("lineNumber", line.getLineNumber());
                jsonObject.addProperty("lineName", line.getLineName());
                jsonArray.add(jsonObject);
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(jsonArray, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


