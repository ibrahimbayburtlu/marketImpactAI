package com.ibrahimbayburtlu.MarketImpactAI.service.Interface;

import com.ibrahimbayburtlu.MarketImpactAI.entity.News;

import java.util.List;

public interface INewsService {
    public List<News> fetchNews(String asset);
    public List<News> saveNews(List<News> newsList);
    public String getNewsText(String asset);
    public List<News> getLatestNews(String asset);
}
