package com.vivek.stocksentry.controller;

import com.vivek.stocksentry.model.StockPrice;
import com.vivek.stocksentry.service.FinnhubService;
import com.vivek.stocksentry.service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "http://localhost:3000")
public class StockDataController {

    @Autowired
    private FinnhubService finnhubService;

    @Autowired
    private NewsApiService newsApiService;

    @GetMapping("/price/{symbol}")
    public StockPrice getStockPrice(
            @PathVariable String symbol) {
        return finnhubService.getStockPrice(symbol);
    }

    @GetMapping("/news/{symbol}")
    public Object getStockNews(
            @PathVariable String symbol) {

        return newsApiService.getNews(symbol);
    }
}