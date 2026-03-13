// language: java
package com.ibrahimbayburtlu.MarketImpactAI.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentimentAnalysis {

    private String asset;
    private String impact;
    private double sentimentScore;
    private double confidence;
    private String reason;
}