package com.example.springExample.services;

import com.example.springExample.Dto.Category;
import com.example.springExample.Dto.News;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;


public class CategoryService {

    private final ConcurrentHashMap<Long, News> categoryNumber = new ConcurrentHashMap<>();
    private long currentId = 1;

//    public Category getById (Long id) {
//       return categoryNumber.get(id).getCategory();
//    }

}
