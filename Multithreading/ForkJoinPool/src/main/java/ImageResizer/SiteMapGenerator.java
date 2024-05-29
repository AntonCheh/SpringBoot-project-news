package ImageResizer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SiteMapGenerator {

    private static final String DOMAIN = "lenta.ru";
    private static final String START_URL = "https://lenta.ru/";
    private static final Set<String> visitedUrls = new HashSet<>();
    private static final ForkJoinPool forkJoinPool = new ForkJoinPool(4);
    private static final int MAX_VISITED = 100; // Ограничение на количество посещенных страниц

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
                if (visitedUrls.size() >= MAX_VISITED || visitedUrls.contains(url)) {
                    return "";
                }
                visitedUrls.add(url);
            }

            StringBuilder result = new StringBuilder();
            String indent = "\t".repeat(depth);
            result.append(indent).append(url).append("\n");

            try {
                Document document = fetchDocument(url);
                if (document == null) {
                    return "";
                }
                Elements links = document.select("a[href]");

                for (Element link : links) {
                    String linkUrl = link.absUrl("href");
                    if (isValidUrl(linkUrl) && !visitedUrls.contains(linkUrl)) {
                        LinkCrawler subTask = new LinkCrawler(linkUrl, depth + 1);
                        subTask.fork();
                        result.append(subTask.join());
                    }
                }

                Thread.sleep((int) (100 + Math.random() * 50));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

            return result.toString();
        }

        private Document fetchDocument(String url) {
            int attempts = 3;
            int timeout = 10000; //

            while (attempts > 0) {
                try {
                    return Jsoup.connect(url).timeout(timeout).ignoreContentType(true).get();
                } catch (HttpStatusException | UnsupportedMimeTypeException e) {
                    System.err.println("HTTP error or unsupported MIME type for URL: " + url);
                    break;
                } catch (IOException e) {
                    attempts--;
                    if (e instanceof java.net.UnknownHostException) {
                        System.err.println("Unknown host: " + e.getMessage());
                        break;
                    }
                    if (attempts > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }

    private static boolean isValidUrl(String url) {
        return url.startsWith("https://" + DOMAIN) && !url.contains("#") && !url.contains("mailto:") && !url.contains("javascript:");
    }
}