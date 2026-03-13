package com.ibrahimbayburtlu.MarketImpactAI.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;

@Document(collection = "sentiment_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SentimentHistory {

    @Id
    private String id;

    private String asset;

    private String impact;

    private double sentimentScore;

    private double confidence;

    private String reason;

    private Instant createdAt;
}