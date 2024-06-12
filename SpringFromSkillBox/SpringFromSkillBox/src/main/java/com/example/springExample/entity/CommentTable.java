package com.example.springExample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "comment")
public class CommentTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorTable author;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime time;
}