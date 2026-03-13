package com.ibrahimbayburtlu.MarketImpactAI.repository;

import com.ibrahimbayburtlu.MarketImpactAI.entity.Analysis;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AnalysisRepository extends MongoRepository<Analysis, String> {

    Optional<Analysis> findByNewsId(String newsId);

    List<Analysis> findByAssetOrderByAnalyzedAtDesc(String asset);

}