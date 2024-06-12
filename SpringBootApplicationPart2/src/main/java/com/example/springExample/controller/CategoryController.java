package com.example.springExample.controller;

import com.example.springExample.Dto.Category;
import com.example.springExample.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Collection<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    public void create(@RequestBody Category category) {
        categoryService.create(category);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
