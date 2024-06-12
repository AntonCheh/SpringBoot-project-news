package com.example.springExample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "news")
public class NewsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //для указания как будут генерироваться при вставке данных
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryTable category;
}
