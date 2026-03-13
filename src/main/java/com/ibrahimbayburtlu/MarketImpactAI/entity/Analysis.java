package com.ibrahimbayburtlu.MarketImpactAI.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Instant;

@Document(collection = "analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Analysis {

    @Id
    private String id;

    @Indexed
    private String newsId;

    @Indexed
    private String asset;

    private String sentiment;

    private Double impactScore;

    private Double confidence;

    private String reason;

    private Instant analyzedAt;
}