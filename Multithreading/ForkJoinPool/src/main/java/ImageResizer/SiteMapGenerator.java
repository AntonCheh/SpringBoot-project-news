package ImageResizer;

import java.io.BufferedWriter;
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

    private static final String ROOT_URL = "https://sendel.com";
    private static final String OUTPUT_FILE = "sitemap.txt";
    private static final Set<String> visitedUrls = new HashSet<>();

    public static void main(String[] args) throws IOException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new SiteMapTask(ROOT_URL, 0));
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SiteMapTask extends RecursiveTask<Void> {
        private final String url;
        private final int depth;

        SiteMapTask(String url, int depth) {
            this.url = url;
            this.depth = depth;
        }

        @Override
        protected Void compute() {
            if (visitedUrls.contains(url) || !url.startsWith(ROOT_URL) || url.contains("#")) {
                return null;
            }

            visitedUrls.add(url);

            try {
                Document document = Jsoup.connect(url).get();
                Elements links = document.select("a[href]");

                saveUrl(url, depth);

                Set<SiteMapTask> tasks = new HashSet<>();
                for (Element link : links) {
                    String absUrl = link.absUrl("href");
                    if (absUrl.startsWith(ROOT_URL) && !visitedUrls.contains(absUrl) && !absUrl.contains("#")) {
                        SiteMapTask task = new SiteMapTask(absUrl, depth + 1);
                        tasks.add(task);
                        task.fork();
                    }
                }

                for (SiteMapTask task : tasks) {
                    task.join();
                }

                // Pause between requests to avoid blocking
                Thread.sleep(100 + (int) (Math.random() * 50));

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void saveUrl(String url, int depth) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
                writer.write("\t".repeat(depth) + url + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}