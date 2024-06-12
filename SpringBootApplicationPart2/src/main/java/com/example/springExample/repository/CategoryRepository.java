package com.example.springExample.repository;


import com.example.springExample.Dto.News;
import com.example.springExample.entity.CategoryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryTable, Long> {

}
