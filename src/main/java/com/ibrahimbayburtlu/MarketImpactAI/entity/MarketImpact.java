package com.ibrahimbayburtlu.MarketImpactAI.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "market_impact")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketImpact {

    @Id
    private String id;

    private String asset;
    private String impact;
    private double sentimentScore;
    private double confidence;
    private String reason;

    @Builder.Default
    private Instant createdAt = Instant.now();

}