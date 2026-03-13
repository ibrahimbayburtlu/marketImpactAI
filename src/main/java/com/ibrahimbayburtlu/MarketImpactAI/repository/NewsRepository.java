package com.ibrahimbayburtlu.MarketImpactAI.repository;

import com.ibrahimbayburtlu.MarketImpactAI.entity.News;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NewsRepository extends MongoRepository<News, String> {

    List<News> findTop5ByAssetOrderByPublishedAtDesc(String asset);

}