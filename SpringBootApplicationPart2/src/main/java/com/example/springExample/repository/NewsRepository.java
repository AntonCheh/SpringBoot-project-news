package com.example.springExample.repository;

import com.example.springExample.entity.NewsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsTable, Long> {
}
