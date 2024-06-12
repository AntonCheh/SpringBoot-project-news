package com.example.springExample.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Setter
@Getter
public class News {
    private long id;
    private String title;
    private String text;
    private Instant date;
    private Category category;
}


