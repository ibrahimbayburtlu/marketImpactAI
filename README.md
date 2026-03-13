# MarketImpactAI

AI powered financial news sentiment and market impact analysis system.

MarketImpactAI analyzes financial news using **Large Language Models (LLM)** and estimates the potential market impact of news on specific assets.

The system fetches financial news, enriches it with company context using **RAG (Retrieval-Augmented Generation)**, and sends the data to an AI model for sentiment and impact analysis.

---

# Features

- Fetch financial news from **News API**
- AI based sentiment & market impact analysis
- Claude API integration
- RAG based company context enrichment
- MongoDB based persistence
- REST API architecture

---

# How It Works

The system processes financial news in the following steps:

1. Fetch financial news using **News API**
2. Store news in **MongoDB**
3. Retrieve company information from a **knowledge base**
4. Combine company context with news content (RAG)
5. Send enriched prompt to **Claude LLM**
6. Generate sentiment & market impact score
7. Store analysis results in the database

---

# Architecture
            +----------------+
            |    News API    |
            +--------+-------+
                     |
                     v
            +----------------+
            | Spring Boot API|
            +--------+-------+
                     |
                     v
            +----------------+
            |     MongoDB    |
            +--------+-------+
                     |
                     v
            +----------------------+
            | Company KnowledgeBase|
            | (JSON / RAG Context) |
            +----------+-----------+
                       |
                       v
                +-------------+
                | Claude API  |
                |   (LLM)     |
                +------+------+
                       |
                       v
             Sentiment & Impact Analysis


             
---

# Example Output

The AI generates structured analysis for each news article.

Example:

```json
{
  "asset": "TESLA",
  "impact": "positive",
  "sentimentScore": 0.67,
  "confidence": 0.82,
  "reason": "Investment in AI infrastructure may improve Tesla's long term technological capabilities."
}
