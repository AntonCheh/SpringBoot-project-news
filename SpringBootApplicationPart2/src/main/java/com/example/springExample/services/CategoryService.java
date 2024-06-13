package com.example.springExample.services;

import com.example.springExample.Dto.Category;
import com.example.springExample.entity.CategoryTable;
import com.example.springExample.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryService implements CRUDservice<Category> {

    private final CategoryRepository repository;

    @Override
    public Category getById(Long id) {
        log.info("Get by ID: " + id);
        CategoryTable table = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
        return mapToDto(table);
    }

    @Override
    public Collection<Category> getAll() {
        log.info("Get all categories");
        return repository.findAll()
                .stream()
                .map(CategoryService::mapToDto)
                .toList();
    }

    @Override
    public void create(Category category) {
        log.info("Create category");
        CategoryTable table = mapToEntity(category);
        repository.save(table);
    }

    @Override
    public void update(Category category) {
        log.info("Update category");
        CategoryTable table = mapToEntity(category);
        repository.save(table);
    }

    @Override
    public void delete(Long id) {
        log.info("Delete category with id: " + id);
        repository.deleteById(id);
    }

    public static Category mapToDto(CategoryTable categoryTable) {
        Category category = new Category();
        category.setId(categoryTable.getId());
        category.setTitle(categoryTable.getTitle());
        category.setNews(categoryTable.getNews()
                .stream()
                .map(NewsService::mapToDto).toList());
        return category;
    }

    public static CategoryTable mapToEntity(Category category) {
        CategoryTable categoryTable = new CategoryTable();
        categoryTable.setId(category.getId());
        categoryTable.setTitle(category.getTitle());
        // Проверяем, что comments не null перед маппингом
        if (category.getNews() != null) {
            categoryTable.setNews(category.getNews()
                    .stream()
                    .map(NewsService::mapToEntity)
                    .toList());
        }
        return categoryTable;
    }
}
