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
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryService implements CRUDservice<Category> {

    private final CategoryRepository repository;

    @Override
    public Category getById(Long id) {
        log.info("Get by ID: " + id);
        CategoryTable table = repository.findById(id).orElseThrow();
        return mapToDto(table);
    }

    @Override
    public Collection<Category> getAll() {
        log.info("Get all");
        return repository.findAll()
                .stream()
                .map(CategoryService::mapToDto)
                .toList();
    }

    @Override
    public void create(Category category) {
        log.info("create");
        CategoryTable table = mapToEntity(category);
        repository.save(table);
    }

    @Override
    public void update(Category category) {
        log.info("Update ");
        CategoryTable table = mapToEntity(category);
        repository.save(table);
    }


    @Override
    public void delete(Long id) {
        System.out.println("delete " + id);
        repository.deleteById(id);
    }

    public static Category mapToDto (CategoryTable categoryTable) {
        Category category = new Category();
        category.setId(categoryTable.getId());
        category.setTitle(categoryTable.getTitle());
        category.setComments(categoryTable.getNews()
                .stream()
                .map(NewsService::mapToDto).toList());
        return category;
    }

    public static CategoryTable mapToEntity (Category category) {
        CategoryTable categoryTable = new CategoryTable();
        categoryTable.setId(category.getId());
        categoryTable.setTitle(category.getTitle());
        categoryTable.setNews(category.getComments()
                .stream()
                .map(NewsService::mapToEntity).toList());
        return categoryTable;
    }
}
