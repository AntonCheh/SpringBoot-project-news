package com.example.springExample.services;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CRUDService <T>{
    T getById (Integer id);
    Collection<T> getAll();
    void create(T item);
    void update(Integer id,T item);
    void delete(Integer id);
}
