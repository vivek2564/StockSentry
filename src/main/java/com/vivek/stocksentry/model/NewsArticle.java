package com.vivek.stocksentry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NewsArticle {
    private String title;
    private String description;
    private String url;
    private String source;
    private String publishedAt;
}