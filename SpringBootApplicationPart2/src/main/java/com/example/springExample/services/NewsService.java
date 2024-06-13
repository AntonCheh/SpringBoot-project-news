package com.example.springExample.services;

import com.example.springExample.Dto.Category;
import com.example.springExample.Dto.News;
import com.example.springExample.entity.CategoryTable;
import com.example.springExample.entity.NewsTable;
import com.example.springExample.repository.CategoryRepository;
import com.example.springExample.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class NewsService implements CRUDservice<News> {

private final NewsRepository newsRepository;
private final CategoryRepository categoryRepository;

    @Override
    public News getById(Long id) {
        log.info("Get by ID: " + id);
        NewsTable table = newsRepository.findById(id).orElseThrow();
        return mapToDto(table);
    }

    @Override
    public Collection<News> getAll() {
        log.info("Get all");
        return newsRepository.findAll()
                .stream()
                .map(NewsService::mapToDto)
                .toList();
    }

    @Override
    public void create(News news) {
        log.info("create");
        NewsTable table = mapToEntity(news);
        Long categoryId = news.getCategoryId();
        CategoryTable category = categoryRepository.findById(categoryId).orElseThrow();
        table.setCategory(category);
        newsRepository.save(table);
    }

    @Override
    public void update(News news) {
        log.info("Update ");
        NewsTable table = mapToEntity(news);
        Long categoryId = news.getCategoryId();
        CategoryTable category = categoryRepository.findById(categoryId).orElseThrow();
        table.setCategory(category);
        newsRepository.save(table);
    }


    @Override
    public void delete(Long id) {
        System.out.println("delete " + id);
        newsRepository.deleteById(id);
    }

    public static News mapToDto (NewsTable newsTable) {
        News news = new News();
        news.setId(newsTable.getId());
        news.setText(newsTable.getText());
        news.setTitle(newsTable.getTitle());
        news.setDate(newsTable.getDate());
        news.setCategoryId(newsTable.getCategory().getId());
        return news;
    }

    public static NewsTable mapToEntity (News news) {
        NewsTable newsTable = new NewsTable();
        newsTable.setId(news.getId());
        newsTable.setText(news.getText());
        newsTable.setTitle(news.getTitle());
        return newsTable;
    }

}
