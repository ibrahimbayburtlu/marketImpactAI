package com.ibrahimbayburtlu.MarketImpactAI.service.Interface;

import com.ibrahimbayburtlu.MarketImpactAI.entity.Analysis;

public interface IAnalysisService {

    Analysis analyzeNews(Long newsId);

    Analysis getAnalysisByNewsId(Long newsId);

}