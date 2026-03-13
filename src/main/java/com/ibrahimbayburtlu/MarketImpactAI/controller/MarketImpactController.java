package com.ibrahimbayburtlu.MarketImpactAI.controller;

import com.ibrahimbayburtlu.MarketImpactAI.entity.Analysis;
import com.ibrahimbayburtlu.MarketImpactAI.service.MarketImpactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/impact")
public class MarketImpactController {

    private final MarketImpactService marketImpactService;

    public MarketImpactController(MarketImpactService marketImpactService) {
        this.marketImpactService = marketImpactService;
    }

    @GetMapping("/{asset}")
    public List<Analysis> getImpact(@PathVariable String asset) {
        return marketImpactService.getImpactByAsset(asset);
    }
}