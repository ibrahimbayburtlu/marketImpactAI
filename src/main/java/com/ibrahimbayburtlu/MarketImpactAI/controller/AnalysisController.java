package com.ibrahimbayburtlu.MarketImpactAI.controller;

import com.ibrahimbayburtlu.MarketImpactAI.entity.Analysis;
import com.ibrahimbayburtlu.MarketImpactAI.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    private static final Logger log = LoggerFactory.getLogger(AnalysisController.class);

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/{newsId}")
    public ResponseEntity<Analysis> analyzeNews(@PathVariable Long newsId) {

        log.info("API request: analyze news {}", newsId);

        Analysis analysis = analysisService.analyzeNews(newsId);

        return ResponseEntity.ok(analysis);
    }

    @GetMapping("/{newsId}")
    public ResponseEntity<Analysis> getAnalysis(@PathVariable Long newsId) {

        log.info("API request: get analysis for news {}", newsId);

        Analysis analysis = analysisService.getAnalysisByNewsId(newsId);

        return ResponseEntity.ok(analysis);
    }
}