// File: src/main/java/com/ibrahimbayburtlu/MarketImpactAI/dto/NewsApiResponse.java
package com.ibrahimbayburtlu.MarketImpactAI.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiResponse {

    private List<Article> articles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Article {
        private String title;
        private String description;
        private String url;
        private String publishedAt;
    }
}