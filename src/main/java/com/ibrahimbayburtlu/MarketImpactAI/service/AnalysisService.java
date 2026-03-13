package com.ibrahimbayburtlu.MarketImpactAI.service;

import com.ibrahimbayburtlu.MarketImpactAI.entity.Analysis;
import com.ibrahimbayburtlu.MarketImpactAI.entity.Company;
import com.ibrahimbayburtlu.MarketImpactAI.entity.News;
import com.ibrahimbayburtlu.MarketImpactAI.dto.SentimentAnalysis;
import com.ibrahimbayburtlu.MarketImpactAI.repository.AnalysisRepository;
import com.ibrahimbayburtlu.MarketImpactAI.repository.NewsRepository;
import com.ibrahimbayburtlu.MarketImpactAI.service.Interface.IAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AnalysisService implements IAnalysisService {

    private static final Logger log = LoggerFactory.getLogger(AnalysisService.class);

    private final ChatClient chatClient;
    private final NewsService newsService;
    private final NewsRepository newsRepository;
    private final AnalysisRepository analysisRepository;
    private final CompanyService companyService;

    public AnalysisService(ChatClient chatClient,
                           NewsService newsService,
                           NewsRepository newsRepository,
                           AnalysisRepository analysisRepository, CompanyService companyService) {
        this.chatClient = chatClient;
        this.newsService = newsService;
        this.newsRepository = newsRepository;
        this.analysisRepository = analysisRepository;
        this.companyService = companyService;
    }

    @Override
    public Analysis analyzeNews(Long newsId) {

        log.info("Starting analysis for news {}", newsId);

        News news = newsRepository.findById(String.valueOf(newsId))
                .orElseThrow(() -> new RuntimeException("News not found"));

        String asset = news.getAsset();

        String newsText = newsService.getNewsText(asset);

        if (newsText == null || newsText.isBlank()) {
            throw new RuntimeException("No news available for analysis");
        }

        // RAG: Company context retrieval
        Company company = companyService.getCompany(asset);

        String companyContext = "";

        if (company != null) {
            companyContext = """
                Company Information:
                Name: %s
                Sector: %s
                Industry: %s
                Description: %s
                """.formatted(
                    company.getName(),
                    company.getSector(),
                    company.getIndustry(),
                    company.getDescription()
            );
        }

        String prompt = """
            You are a financial analyst AI.

            Use the following company context while analyzing the news.

            %s

            Analyze the following financial news and determine the overall market impact on %s.

            News:
            %s

            Return ONLY valid JSON.

            JSON fields:
            asset
            impact (positive, negative, neutral, mixed)
            sentimentScore (-1 to 1)
            confidence (0 to 1)
            reason
            """.formatted(companyContext, asset, newsText);

        long start = System.currentTimeMillis();

        SentimentAnalysis result = chatClient.prompt()
                .user(prompt)
                .call()
                .entity(SentimentAnalysis.class);

        long end = System.currentTimeMillis();

        log.info("AI analysis completed in {} ms for asset {}", (end - start), asset);

        Analysis analysis = Analysis.builder()
                .newsId(newsId.toString())
                .asset(result.getAsset())
                .sentiment(result.getImpact())
                .impactScore(result.getSentimentScore())
                .confidence(result.getConfidence())
                .reason(result.getReason())
                .analyzedAt(Instant.now())
                .build();

        return analysisRepository.save(analysis);
    }

    @Override
    public Analysis getAnalysisByNewsId(Long newsId) {

        log.info("Fetching analysis for news {}", newsId);

        return analysisRepository.findByNewsId(newsId.toString())
                .orElseThrow(() -> new RuntimeException("Analysis not found"));
    }
}