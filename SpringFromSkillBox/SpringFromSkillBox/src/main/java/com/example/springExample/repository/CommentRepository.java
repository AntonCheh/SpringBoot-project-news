package com.example.springExample.repository;

import com.example.springExample.entity.CommentTable;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface CommentRepository extends JpaRepository<CommentTable, Integer> {

}
