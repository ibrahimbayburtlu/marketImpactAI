package com.ibrahimbayburtlu.MarketImpactAI.service;

import com.ibrahimbayburtlu.MarketImpactAI.entity.Analysis;
import com.ibrahimbayburtlu.MarketImpactAI.repository.AnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketImpactService {

    private final AnalysisRepository analysisRepository;

    public MarketImpactService(AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }

    public List<Analysis> getImpactByAsset(String asset) {
        return analysisRepository.findByAssetOrderByAnalyzedAtDesc(asset);
    }
}