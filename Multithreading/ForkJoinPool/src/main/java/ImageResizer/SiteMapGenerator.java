package ImageResizer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SiteMapGenerator {

    private static final String DOMAIN = "lenta.ru";
    private static final String START_URL = "https://lenta.ru/";
    private static final Set<String> visitedUrls = new HashSet<>();
    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("sitemap.txt")) {
            LinkCrawler task = new LinkCrawler(START_URL, 0);
            String result = forkJoinPool.invoke(task);
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class LinkCrawler extends RecursiveTask<String> {
        private final String url;
        private final int depth;

        public LinkCrawler(String url, int depth) {
            this.url = url;
            this.depth = depth;
        }

        @Override
        protected String compute() {
            synchronized (visitedUrls) {
                if (visitedUrls.contains(url)) {
                    return "";
                }
                visitedUrls.add(url);
            }

            StringBuilder result = new StringBuilder();
            String indent = "\t".repeat(depth);
            result.append(indent).append(url).append("\n");

            try {
                Document document = Jsoup.connect(url).get();
                Elements links = document.select("a[href]");

                for (Element link : links) {
                    String linkUrl = link.absUrl("href");
                    if (isValidUrl(linkUrl) && !visitedUrls.contains(linkUrl)) {
                        LinkCrawler subTask = new LinkCrawler(linkUrl, depth + 1);
                        subTask.fork();
                        String childResult = subTask.join();
                        String childIndent = "\t".repeat(depth + 1);
                        result.append(childIndent).append(childResult);
                    }
                }

                // Проверяем, если нет больше ссылок, то выходим из цикла
                if (links.isEmpty()) {
                    return "";
                }

                Thread.sleep((int) (100 + Math.random() * 50));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return result.toString();
        }
    }

    private static boolean isValidUrl(String url) {
        return url.startsWith("https://" + DOMAIN) && !url.contains("#");
    }
}