package com.example.springExample.beans;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final ConcurrentHashMap<Long, News> newsStore = new ConcurrentHashMap<>();
    private long currentId = 1;

    public News getById(Long id) {

        return newsStore.get(id);
    }

    public List<News> getAll() {

        return newsStore.values().stream().collect(Collectors.toList());
    }

    public News create(News news) {
        news.setId(currentId++);
        newsStore.put(news.getId(), news);
        return news;
    }

    public News update(News news) {
        newsStore.put(news.getId(), news);
        return news;
    }

    public void deleteById(Long id) {
        newsStore.remove(id);
    }
}
