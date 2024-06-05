package com.example.springExample.controller;

import com.example.springExample.beans.News;
import com.example.springExample.beans.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        News news = newsService.getById(id);
        if (news != null) {
            return ResponseEntity.ok(news);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Новость с ID " + id + " не найдена.\"}");
        }
    }

    @GetMapping
    public ResponseEntity<List<News>> getAll() {

        return ResponseEntity.ok(newsService.getAll());
    }

    @PostMapping
    public ResponseEntity<News> create(@RequestBody News news) {
        News createdNews = newsService.create(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
    }

    @PutMapping
    public ResponseEntity<News> update(@RequestBody News news) {
        News updatedNews = newsService.update(news);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        News news = newsService.getById(id);
        if (news != null) {
            newsService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Новость с ID " + id + " не найдена.\"}");
        }
    }
}


