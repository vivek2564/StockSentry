package com.vivek.stocksentry.service;

import com.vivek.stocksentry.model.NewsArticle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NewsApiService {

    @Value("${newsapi.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL =
            "https://newsapi.org/v2/everything?q={symbol}&language=en&pageSize=5&apiKey={apikey}";

    public List<NewsArticle> getNews(String symbol) {
        try {
            Map<?, ?> response = restTemplate.getForObject(
                    BASE_URL, Map.class, symbol, apiKey);

            if (response == null || !response.containsKey("articles")) {
                return List.of();
            }

            List<?> articles = (List<?>) response.get("articles");
            List<NewsArticle> result = new ArrayList<>();

            for (Object obj : articles) {
                Map<?, ?> article = (Map<?, ?>) obj;
                Map<?, ?> source = (Map<?, ?>) article.get("source");

                NewsArticle newsArticle = new NewsArticle();
                newsArticle.setTitle((String) article.get("title"));
                newsArticle.setDescription((String) article.get("description"));
                newsArticle.setUrl((String) article.get("url"));
                newsArticle.setSource(source != null ? (String) source.get("name") : "Unknown");
                newsArticle.setPublishedAt((String) article.get("publishedAt"));

                result.add(newsArticle);
            }

            return result;

        } catch (Exception e) {
            System.err.println("Error fetching news for " + symbol + ": " + e.getMessage());
            return List.of();
        }
    }
}