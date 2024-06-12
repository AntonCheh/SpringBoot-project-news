package com.example.springExample.services;

import java.util.Collection;

public interface CRUDservice<T> {
    T getById(Long id);
    Collection<T> getAll();
    void create (T news);
    void update (Long id, T news);
    void delete (Long id);
}
