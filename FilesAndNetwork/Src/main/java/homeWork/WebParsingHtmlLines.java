package homeWork;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class WebParsingHtmlLines {

//    public static void main(String[] args) {
//print();
//    }

    public static void print (){

        String htmlFile = parseFile(Path.of("data/code.html"));
        Document document = Jsoup.parse(htmlFile);
        Elements elements = document.select("div.js-toggle-depend");

        elements.forEach (element -> {
            String lineNumber = getLineNumber(element);
            String lineName = element.select("span.js-metro-line").text();

            System.out.println("Номер линии: " + lineNumber);
            System.out.println("Название линии: " + lineName);
        });
    }
    private static String getLineNumber(Element element) {
        String lineData = element.attr("data-depend");
        String[] parts = lineData.split("'ln-");
        if (parts.length > 1) {
            return parts[1].split("'")[0];
        } else {
            String dataLine = element.select("span.js-metro-line").attr("data-line");
            return dataLine.isEmpty() ? "N/A" : dataLine;
        }
    }
    private static String parseFile(Path path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(path)));
            lines.forEach(line -> builder.append(line + "\n") );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}







