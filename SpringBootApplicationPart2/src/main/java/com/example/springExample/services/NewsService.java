package com.example.springExample.services;

import com.example.springExample.Dto.News;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class NewsService implements CRUDservice<News> {

    private final TreeMap<Long, News> storage = new TreeMap<>();

    @Override
    public News getById(Long id) {
        System.out.println("Get by ID: " + id);
        return storage.get(id);
    }

    @Override
    public Collection<News> getAll() {
        System.out.println("Get all");
        return storage.values();
    }

    @Override
    public void create(News news) {
        System.out.println("create");
        Long nextId = storage.isEmpty() ? 1L : storage.lastKey() + 1;
        news.setId(nextId);
        storage.put(nextId, news);
    }

    @Override
    public void update(Long id, News news) {
        System.out.println("Update " + id);
        if (!storage.containsKey(id)) {
            return;
        }
        news.setId(id);
        storage.put(id, news);
    }

    @Override
    public void delete(Long id) {
        System.out.println("delete " + id);
        storage.remove(id);
    }
}
