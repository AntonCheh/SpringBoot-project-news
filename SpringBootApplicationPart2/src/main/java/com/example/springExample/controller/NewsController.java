package com.example.springExample.controller;

import com.example.springExample.Dto.News;
import com.example.springExample.services.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {

        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public News getByIdNews(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @GetMapping
    public Collection<News> getAllNews() {
        return newsService.getAll();
    }

    @PostMapping
    public void createNews(@RequestBody News news) {
        newsService.create(news);
    }

    @PutMapping("/{id}")
    public void updateNews(@PathVariable Long id, @RequestBody News news) {
         news.setId(id);
        newsService.update(news);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
         newsService.delete(id);
    }
}


