package com.example.springExample.services;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CRUDService <T>{
    public T getById (Integer id);
    Collection<T> getAll();
    void create(T item);
    void update(T item);
    void delete(Integer id);
}
