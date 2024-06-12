package com.example.springExample.services;

import com.example.springExample.Dto.News;

import java.util.Collection;

public interface CRUDservice<T> {
    T getById(Long id);
    Collection<T> getAll();
    void create (T news);
    void update (T news);
    void delete (Long id);
}
