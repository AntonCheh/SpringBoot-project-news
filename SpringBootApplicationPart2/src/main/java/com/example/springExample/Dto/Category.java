package com.example.springExample.Dto;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private Long id;
    private String title;
    private List<News> news;
}
