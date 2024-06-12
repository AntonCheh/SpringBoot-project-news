package com.example.springExample.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class News {
    private long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private Long categoryId;
}


