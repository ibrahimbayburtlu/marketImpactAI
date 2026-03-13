package com.ibrahimbayburtlu.MarketImpactAI.service;

import com.ibrahimbayburtlu.MarketImpactAI.entity.News;
import com.ibrahimbayburtlu.MarketImpactAI.dto.NewsApiResponse;
import com.ibrahimbayburtlu.MarketImpactAI.repository.NewsRepository;
import com.ibrahimbayburtlu.MarketImpactAI.service.Interface.INewsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService implements INewsService {

    private final RestTemplate restTemplate;
    private final NewsRepository newsRepository;

    @Value("${news.api.url}")
    private String apiUrl;

    @Value("${news.api.key}")
    private String apiKey;

    private static final Logger log = LoggerFactory.getLogger(NewsService.class);

    public NewsService(
            RestTemplate restTemplate,
            NewsRepository newsRepository
    ) {
        this.restTemplate = restTemplate;
        this.newsRepository = newsRepository;
    }

    public List<News> fetchNews(String asset) {

        log.info("Fetching news for asset: {}", asset);

        String url = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .queryParam("q", asset)
                .queryParam("sortBy", "publishedAt")
                .queryParam("apiKey", apiKey)
                .toUriString();

        NewsApiResponse response =
                restTemplate.getForObject(url, NewsApiResponse.class);

        if (response == null || response.getArticles() == null) {
            log.warn("No news returned for asset: {}", asset);
            return Collections.emptyList();
        }

        log.info("Fetched {} news articles for asset {}",
                response.getArticles().size(), asset);

        return response.getArticles()
                .stream()
                .limit(5)
                .map(article -> News.builder()
                        .asset(asset)
                        .title(article.getTitle())
                        .description(
                                article.getDescription() != null ? article.getDescription() : ""
                        )
                        .url(article.getUrl())
                        .publishedAt(article.getPublishedAt())
                        .build()
                )
                .toList();
    }

    public List<News> saveNews(List<News> newsList) {

        List<News> savedNews = newsRepository.saveAll(newsList);

        log.info("Embedded {} news articles into vector store", savedNews.size());

        return savedNews;
    }

    public String getNewsText(String asset) {

        List<News> news = newsRepository
                .findTop5ByAssetOrderByPublishedAtDesc(asset);

        return news.stream()
                .map(n -> String.format(
                        "Title: %s\nDescription: %s",
                        n.getTitle(),
                        n.getDescription() != null ? n.getDescription() : ""
                ))
                .collect(Collectors.joining("\n\n"));
    }

    public List<News> getLatestNews(String asset) {
        return newsRepository.findTop5ByAssetOrderByPublishedAtDesc(asset);
    }
}