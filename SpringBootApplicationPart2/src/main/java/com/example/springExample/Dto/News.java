package com.example.springExample.Dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private long id;
    private String title;
    private String text;
    private LocalDateTime date;
    private Long categoryId;
}


