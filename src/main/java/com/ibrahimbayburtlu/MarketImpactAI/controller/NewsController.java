package com.ibrahimbayburtlu.MarketImpactAI.controller;

import com.ibrahimbayburtlu.MarketImpactAI.entity.News;
import com.ibrahimbayburtlu.MarketImpactAI.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // Fetch news from external API and save
    @PostMapping("/fetch")
    public List<News> fetchNews(@RequestParam String asset) {
        List<News> news = newsService.fetchNews(asset);
        return newsService.saveNews(news);
    }

    // Get latest news from DB
    @GetMapping
    public List<News> getNews(@RequestParam String asset) {
        return newsService.getLatestNews(asset);
    }
}