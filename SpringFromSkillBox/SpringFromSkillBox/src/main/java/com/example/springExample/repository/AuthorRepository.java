package com.example.springExample.repository;


import com.example.springExample.entity.AuthorTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorTable, Integer> {
}
