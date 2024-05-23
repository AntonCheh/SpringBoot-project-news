package ImageResizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;


public class SiteMapGenerator {

    private static final String DOMAIN = "lenta.ru";
    private static final String START_URL = "https://lenta.ru/";
    private static final Set<String> visitedUrls = new HashSet<>();
    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("sitemap.txt")) {
            forkJoinPool.invoke(new LinkCrawler(START_URL, 0, writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class LinkCrawler extends RecursiveAction {
        private final String url;
        private final int depth;
        private final FileWriter writer;

        public LinkCrawler(String url, int depth, FileWriter writer) {
            this.url = url;
            this.depth = depth;
            this.writer = writer;
        }

        @Override
        protected void compute() {
            synchronized (visitedUrls) {
                if (visitedUrls.contains(url)) {
                    return;
                }
                visitedUrls.add(url);
            }

            try {
                Document document = Jsoup.connect(url).get();
                Elements links = document.select("a[href]");

                String indent = "\t".repeat(depth);
                synchronized (writer) {
                    writer.write(indent + url + "\n");
                }

                for (Element link : links) {
                    String linkUrl = link.absUrl("href");
                    if (isValidUrl(linkUrl) && !visitedUrls.contains(linkUrl)) {
                        String childIndent = "\t".repeat(depth + 1);
                        synchronized (writer) {
                            writer.write(childIndent + linkUrl + "\n");
                        }
                        forkJoinPool.execute(new LinkCrawler(linkUrl, depth + 1, writer));
                    }
                }

                // Pause to prevent blocking
                Thread.sleep((int) (100 + Math.random() * 50));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isValidUrl(String url) {
        return url.startsWith("https://" + DOMAIN) && !url.contains("#");
    }
}